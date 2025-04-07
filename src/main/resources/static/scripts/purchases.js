//parte del tamplate
let userData
window.addEventListener("DOMContentLoaded", async () => {
  const roleH1 = document.querySelector("#title");
  const optionsNav = document.querySelector("#nav-options");

  userData = JSON.parse(localStorage.getItem("userData"));

  roleH1.textContent = userData.rol.nombreRol;
  let permisoCrear=true

  userData.rol.permisos.forEach((p) => {
    if (p.acceso && p.permiso.accesoDirecto) {
      const option = document.createElement("a");
      option.classList.add("nav");
      option.textContent = p.permiso.descripcion;
      option.dataset.id = p.permiso.idPermiso;

      option.href = p.permiso.endpointUrl;
      optionsNav.appendChild(option);
    }
    if(p.permiso.descripcion == "Compras" && p.creacion==true){
      permisoCrear=true
    }
   
  });

  document.querySelector("#logout-btn").addEventListener("click", () => {
    window.localStorage.removeItem("userData");
    window.location.href = "/login";
})

  
 
    if(permisoCrear){
      const guardar =document.querySelector("#createPurchase")
      guardar.disabled=false
      const buttonagregar=document.querySelector("#addProducts")
 buttonagregar.disabled=false
    guardar.addEventListener("click",crearCompra);
  }
   
   
  
  await cargarProveedores()
  await cargarCompras();
 

});

const combobox = document.querySelector("#suppliers");
let selectedRow = null;
let currentPage = 1;
const itemsPerPage = 5;
let allProducts = [];

//hace que se puedan selecionar los datos de la tabla
function seleccionar() {
  // Selecciona todas las filas de la tabla
  const rows = document.querySelectorAll("#productos tbody tr");

  // Recorre las filas y añade un evento de clic para seleccionarlas
  rows.forEach((row) => {
    row.addEventListener("click", function () {
      // Elimina la clase "seleccionado" de todas las filas
      rows.forEach((r) => r.classList.remove("table-active"));

      // Añade la clase "seleccionado" a la fila clicada
      this.classList.add("table-active");

      // Guarda la fila seleccionada
      selectedRow = this;
    });
  });
}

function soloNumeros(e) {
  // Permitir teclas de control (backspace, delete, tab, etc.)
  if ([8, 9, 13, 27, 46].includes(e.keyCode) || 
      // Permitir Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+X
      (e.keyCode === 65 && e.ctrlKey === true) || 
      (e.keyCode === 67 && e.ctrlKey === true) ||
      (e.keyCode === 86 && e.ctrlKey === true) ||
      (e.keyCode === 88 && e.ctrlKey === true) ||
      // Permitir navegación con flechas
      (e.keyCode >= 35 && e.keyCode <= 39)) {
      return;
  }
  
  // No permitir letras u otros caracteres
  if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
      e.preventDefault();
  }
}

// Función para permitir números y punto decimal
function soloNumerosYDecimales(e) {
  // Permitir teclas de control
  if ([8, 9, 13, 27, 46, 110, 190].includes(e.keyCode) || 
      // Permitir Ctrl+A, Ctrl+C, Ctrl+V, Ctrl+X
      (e.keyCode === 65 && e.ctrlKey === true) || 
      (e.keyCode === 67 && e.ctrlKey === true) ||
      (e.keyCode === 86 && e.ctrlKey === true) ||
      (e.keyCode === 88 && e.ctrlKey === true) ||
      // Permitir navegación con flechas
      (e.keyCode >= 35 && e.keyCode <= 39)) {
      // Permitir solo un punto decimal
      if ((e.keyCode === 110 || e.keyCode === 190) && e.target.value.includes('.')) {
          e.preventDefault();
      }
      return;
  }
  
  // No permitir letras u otros caracteres
  if ((e.keyCode < 48 || e.keyCode > 57) && (e.keyCode < 96 || e.keyCode > 105)) {
      e.preventDefault();
  }
}

// Función para enviar los datos seleccionados a la tabla de destino
document.getElementById("sendDataBtn").addEventListener("click", function () {
  const rowsProductosCompra = document.querySelectorAll("#productosCompra tbody tr");
  if (selectedRow) {
    // Obtén los datos de la fila seleccionada
    const cells = selectedRow.querySelectorAll("td");
    const ids = [];
    if (rowsProductosCompra != null) {
      rowsProductosCompra.forEach((r) => {
        const cell = r.querySelectorAll("td");
        ids.push(cell[0].innerHTML);
      });
    }
    if (!ids.includes(cells[0].innerHTML)) {
      const data = [
        cells[0].innerText, // id
        cells[1].innerText, // Producto
      ];

      // Crea una nueva fila en la tabla de destino
      const newRow = document.createElement("tr");
      data.forEach((text) => {
        const newCell = document.createElement("td");
        newCell.innerText = text;
        newRow.appendChild(newCell);
      });
      const inputs = [];

      let costo = document.createElement("input");
      costo.type = "text";
      costo.classList.add = "cost form-control";
      costo.addEventListener('input', calcularTotal);
      costo.type = "number";  // Cambiado de "text" a "number"
      costo.step = "0.01";    // Para permitir decimales
      costo.min = "0";        // Valor mínimo 0
      
      costo.addEventListener("keydown", soloNumerosYDecimales);

      let cantidad = document.createElement("input");
      cantidad.type = "number";
      cantidad.value = "1";
      cantidad.min = "1";
      cantidad.addEventListener('input', calcularTotal);
      cantidad.classList.add="quantity form-control input-number form-control input-number";
      cantidad.addEventListener("keydown", soloNumeros);
      let sacar = document.createElement("button");
      sacar.textContent = "Eliminar";
      sacar.className = "btn btn-danger";
      calcularTotal();
      sacar.onclick = function () {
        this.parentNode.parentNode.remove();
        verificarTabla();
      };
      inputs.push(costo)
      inputs.push(cantidad);
      inputs.push(sacar);

      inputs.forEach((i) => {
        const newCell = document.createElement("td");
        newCell.appendChild(i);
        newRow.appendChild(newCell);
      });

      // Añade la nueva fila a la tabla de destino
      document.querySelector("#productosCompra tbody").appendChild(newRow);

      //bloquear combobox de provedores si hay un producto selecionado
      combobox.setAttribute("disabled", true);
    } else {
      alert("valor ya agregado");
    }
  } else {
    alert("Por favor, selecciona una fila primero.");
  }
});

function verificarTabla() {
  let tabla = document.getElementById("productosCompra");
  let filas = tabla.getElementsByTagName("tr");

  let comboBox = document.getElementById("suppliers");

  // Si la tabla solo tiene la primera fila (encabezado), habilita la combobox
  if (filas.length === 1) {
    comboBox.disabled = false;
    document.getElementById('totalCost').value = "0.00";
  }
  calcularTotal(); // Recalcular siempre que cambie la tabla
}

async function cargarProveedores() {
  try {
    let response = await fetch("/api/comprar/proveedores");
    let data = await response.json();
    console.log(data);
    const comboBox = document.querySelector("#suppliers");
    data.forEach((d) => {
      const newOPtion = document.createElement("option");
      newOPtion.value=d["idProveedor"]
      newOPtion.setAttribute("data-supplier-id", d["idProveedor"]);
      newOPtion.innerHTML = d["razonSocial"];
      comboBox.appendChild(newOPtion);
    });
  } catch (error) {
    console.error("Error al obtener proveedors:", error);
  }
}

function obtenerSelecion(){
  return combobox.options[combobox.selectedIndex]
}

//agregar productos a la productos
async function cargarProductos() {
  const tabla = document.querySelector("#productos tbody");
  tabla.innerHTML = "";
  try {
    let response = await fetch(
      `/api/comprar/cargarProductos/${obtenerSelecion().dataset.supplierId}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    allProducts = await response.json();
    mostrarPagina(currentPage);
    actualizarInfoPagina();
    seleccionar();
    document.getElementById('totalCost').value = "0.00";
  } catch (error) {
    console.error("Error al obtener proveedors:", error);
  }
}

// Mostrar productos de la página actual
function mostrarPagina(page) {
  const startIndex = (page - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const paginatedProducts = allProducts.slice(startIndex, endIndex);
  
  const tabla = document.querySelector("#productos tbody");
  tabla.innerHTML = "";
  
  paginatedProducts.forEach((d) => {
    const newFila = document.createElement("tr");
    Object.keys(d).forEach((key) => {
      if (key == "idProducto" || key == "nombreProducto") {
        const newCell = document.createElement("td");
        newCell.innerHTML = d[key];
        newFila.appendChild(newCell);
      }
    });
    tabla.appendChild(newFila);
  });
}

// Actualizar información de paginación
function actualizarInfoPagina() {
  const totalPages = Math.ceil(allProducts.length / itemsPerPage);
  document.getElementById('pageInfo').textContent = `Página ${currentPage} de ${totalPages}`;
  
  // Habilitar/deshabilitar botones
  document.getElementById('prevPage').disabled = currentPage === 1;
  document.getElementById('nextPage').disabled = currentPage === totalPages || totalPages === 0;
}

// convierte las filas a de una tabla a JSON
function convertirDatosAJson() {
  // Obtener la tabla y sus filas
  const tabla = document.getElementById('productosCompra');
  const filas = tabla.getElementsByTagName('tr');
  const datosJson = [];

  // Iterar sobre las filas (empezamos desde 1 para omitir la fila de encabezado)
  for (let i = 1; i < filas.length; i++) {
      const celdas = filas[i].getElementsByTagName('td');
      const filaData = {
          id: parseInt(celdas[0].innerText),
          costo: parseFloat(celdas[2].firstElementChild.value),
          cantidad: parseInt(celdas[3].firstElementChild.value)
      };

       datosJson.push(filaData);
  }
  const numFactura = document.querySelector("#invoice")
  const fechaCompra= document.querySelector("#datePurchase")
  datosJson.push({
    numFactura:numFactura.value,
    fechaCompra:fechaCompra.value,
    idSucursal:parseInt( userData.empleado.sucursal.idSucursal)
  })
  return datosJson;
}

async function crearCompra() {
  const total = parseFloat(document.getElementById('totalCost').value);
    if (total <= 0) {
        alert("El costo total debe ser mayor que cero");
        return;
    }
console.log(convertirDatosAJson())

  try {
    let response = await fetch(`/api/comprar/crearCompra/${obtenerSelecion().dataset.supplierId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(convertirDatosAJson())
    });
    if (response.ok) {
      alert("Solicitud creada correctamente");
      // Opcional: limpiar la tabla y reiniciar el formulario
      location.reload();
  } else {
      alert("Error al crear la solicitud");
  }

   
  } catch (error) {
    console.error("Error en la solicitud");
  }
}

function calcularTotal() {
  const tabla = document.getElementById('productosCompra');
  const filas = tabla.getElementsByTagName('tr');
  let total = 0;

  // Iterar sobre las filas (empezamos desde 1 para omitir la fila de encabezado)
  for (let i = 1; i < filas.length; i++) {
      const celdas = filas[i].getElementsByTagName('td');
      const costo = parseFloat(celdas[2].firstElementChild.value) || 0;
      const cantidad = parseInt(celdas[3].firstElementChild.value) || 0;
      total += costo * cantidad;
  }

  // Actualizar el campo de costo total
  document.getElementById('totalCost').value = total.toFixed(2);
}



// Funciones para recepción de compras
async function cargarCompras() {
  try {
    const response = await fetch('/api/comprar/cargarCompras');
    const Compras = await response.json();
    console.log(Compras)
    
    const select = document.getElementById('comprasList');
    select.innerHTML = '<option value="">Seleccione una compra...</option>';
    
    Compras.forEach(solicitud => {
      const option = document.createElement('option');
      option.value = solicitud.idCompra;
      option.textContent = `Compra #${solicitud.idCompra} - ${solicitud.proveedor.razonSocial} - ${new Date(solicitud.fechaCompra).toLocaleDateString()}`;
      option.value=solicitud.idCompra
      select.appendChild(option);
    });
    
    // Event listener para cargar detalles cuando se selecciona una solicitud
    select.addEventListener('change', cargarDetalleCompra);
  } catch (error) {
    console.error('Error al cargar compras', error);
  }

}



async function cargarDatosCompra(idCompra) {
  try {
    let response = await fetch( `/api/comprar/datosCompra/${idCompra}`);
    let data = await response.json();
    console.log(data);
    
   const buttonCompra=document.querySelector("#createPurchase")
   buttonCompra.disabled=true
   const buttonagregar=document.querySelector("#addProducts")
   buttonagregar.disabled=true
   const fechaCompra=data.fechaCompra
   const idProveedor=data.idProveedor
   const factura=document.querySelector("#invoice")
   factura.value=data.numFacturaCompra
   factura.disabled=true
   const fecha= document.querySelector("#datePurchase")
   fecha.value=data.fechaCompra
   fecha.disabled=true
   const proveedor= document.querySelector("#suppliers")
   console.log(data.proveedor.idProveedor)
   proveedor.value=data.proveedor.idProveedor
   proveedor.disabled= true
   cargarProductos()
   
  } catch (error) {
    console.error("Error al obtener la compra", error);
  }
}


async function cargarDetalleCompra() {
  const compraId = document.getElementById('comprasList').value;
  if (!compraId) return;
 cargarDatosCompra(compraId)
  
  try {
    const response = await fetch(`/api/comprar/productosCompra/${compraId}`);
    const detalles = await response.json();
    console.log(detalles)
    
    const tabla = document.querySelector('#productosCompra tbody');
    tabla.innerHTML = '';
    

    detalles.forEach(detalle => {
      const row = document.createElement('tr');
      
      // ID Producto
      const idCell = document.createElement('td');
      idCell.textContent = detalle.producto.idProducto;
      row.appendChild(idCell);
      
      // Nombre Producto
      const nombreCell = document.createElement('td');
      nombreCell.textContent = detalle.producto.nombreProducto;
      row.appendChild(nombreCell);
      
     

      
    
      
      const inputs = [];
    
          let costo = document.createElement("input");
          costo.type = "text";
          costo.classList.add = "cost form-control";
          costo.addEventListener('input', calcularTotal);
          costo.type = "number";  // Cambiado de "text" a "number"
          costo.step = "0.01";    // Para permitir decimales
          costo.min = "0";        // Valor mínimo 0
          costo.value=detalle.costo
          costo.disabled=true
          
          costo.addEventListener("keydown", soloNumerosYDecimales);
    
          let cantidad = document.createElement("input");
          cantidad.type = "number";
          cantidad.value = "1";
          cantidad.min = "1";
          cantidad.addEventListener('input', calcularTotal);
          cantidad.classList.add="quantity form-control input-number form-control input-number";
          cantidad.addEventListener("keydown", soloNumeros);
          cantidad.disabled=true
          cantidad.value=detalle.cantidad
          let sacar = document.createElement("button");
          sacar.textContent = "Eliminar";
          sacar.className = "btn btn-danger";
          sacar.disabled=true
          
          sacar.onclick = function () {
            this.parentNode.parentNode.remove();
            verificarTabla();
          };
          inputs.push(costo)
          inputs.push(cantidad);
          inputs.push(sacar);
    
          inputs.forEach((i) => {
            const newCell = document.createElement("td");
            newCell.appendChild(i);
            row.appendChild(newCell);
          });
    
      
      
      
      
      
      
      
      tabla.appendChild(row);
      calcularTotal();
    });


    
      
      
      
          
          
       


        











    
    
  } catch (error) {
    console.error('Error al cargar detalle de solicitud:', error);
  }
}



// Event listeners para paginación
document.getElementById('prevPage').addEventListener('click', () => {
  if (currentPage > 1) {
    currentPage--;
    mostrarPagina(currentPage);
    actualizarInfoPagina();
  }
});

document.getElementById('nextPage').addEventListener('click', () => {
  const totalPages = Math.ceil(allProducts.length / itemsPerPage);
  if (currentPage < totalPages) {
    currentPage++;
    mostrarPagina(currentPage);
    actualizarInfoPagina();
  }
});

// Búsqueda de productos
document.getElementById('searchBtn').addEventListener('click', buscarProductos);

function buscarProductos() {
  const searchTerm = document.getElementById('searchProduct').value.toLowerCase();
  
  if (searchTerm === '') {
    mostrarPagina(currentPage);
    seleccionar()
    return;
  }
  
  const filteredProducts = allProducts.filter(product => 
    product.nombreProducto.toLowerCase().includes(searchTerm) || 
    product.idProducto.toString().includes(searchTerm)
  );
  
  const tabla = document.querySelector("#productos tbody");
  tabla.innerHTML = "";
  
  filteredProducts.forEach((d) => {
    const newFila = document.createElement("tr");
    Object.keys(d).forEach((key) => {
      if (key == "idProducto" || key == "nombreProducto") {
        const newCell = document.createElement("td");
        newCell.innerHTML = d[key];
        newFila.appendChild(newCell);
      }
    });
    tabla.appendChild(newFila);
  });
  seleccionar()
}

// Inicialización

combobox.addEventListener("change", cargarProductos);

