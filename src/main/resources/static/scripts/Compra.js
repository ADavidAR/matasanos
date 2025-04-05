//parte del tamplate
window.addEventListener("DOMContentLoaded", async () => {
  const roleH1 = document.querySelector("#title");
  const optionsNav = document.querySelector("#nav-options");

  let userData = JSON.parse(localStorage.getItem("userData"));

  roleH1.textContent = userData.rol.nombreRol;

  userData.rol.permisos.forEach((p) => {
    if (p.acceso && p.permiso.accesoDirecto) {
      const option = document.createElement("a");
      option.classList.add("btn");
      option.textContent = p.permiso.descripcion;
      option.dataset.id = p.permiso.idPermiso;

      option.href = p.permiso.endpointUrl;
      optionsNav.appendChild(option);
    }
  });
  console.log(userData)
});

const combobox = document.querySelector("#suppliers");
let selectedRow = null;
//hace que se puedan selecionar los datos de la tabla
function seleccionar() {
  // Selecciona todas las filas de la tabla
  const rows = document.querySelectorAll("#myTable tbody tr");

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
  const rowstable2 = document.querySelectorAll("#table2 tbody tr");
  if (selectedRow) {
    // Obtén los datos de la fila seleccionada
    const cells = selectedRow.querySelectorAll("td");
    const ids = [];
    if (rowstable2 != null) {
      rowstable2.forEach((r) => {
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
      document.querySelector("#table2 tbody").appendChild(newRow);

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
  let tabla = document.getElementById("table2");
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
    let response = await fetch("/api/comprar/cargar");
    let data = await response.json();
 
    const comboBox = document.querySelector("#suppliers");
    data.forEach((d) => {
      const newOPtion = document.createElement("option");
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
//agregar productos a la myTable
async function cargarProductos() {
  
  const tabla = document.querySelector("#myTable tbody");
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
    let data = await response.json();
    

    data.forEach((d) => {
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

    seleccionar();
  } catch (error) {
    console.error("Error al obtener proveedors:", error);
  }
}
// convierte las filas a de una tabla a JSON
function convertirATablaAJson() {
  // Obtener la tabla y sus filas
  const tabla = document.getElementById('table2');
  const filas = tabla.getElementsByTagName('tr');
  const datosJson = [];

  // Iterar sobre las filas (empezamos desde 1 para omitir la fila de encabezado)
  for (let i = 1; i < filas.length; i++) {
      const celdas = filas[i].getElementsByTagName('td');
      const filaData = {
          id: celdas[0].innerText,
          costo: parseFloat(celdas[2].firstElementChild.value),
          cantidad: parseInt(celdas[3].firstElementChild.value)
      };
       datosJson.push(filaData);
  }
  return datosJson;
}

async function crearCompra() {
  const total = parseFloat(document.getElementById('totalCost').value);
    if (total <= 0) {
        alert("El costo total debe ser mayor que cero");
        return;
    }



  try {
    let response = await fetch(`/api/comprar/crearSolicitudCompra/${obtenerSelecion().dataset.supplierId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(convertirATablaAJson())
    });
    if (response.ok) {
      alert("Solicitud creada correctamente");
      // Opcional: limpiar la tabla y reiniciar el formulario
      document.querySelector("#table2 tbody").innerHTML = "";
      document.getElementById('totalCost').value = "0.00";
      combobox.removeAttribute("disabled");
      combobox.selectedIndex = 0;
  } else {
      alert("Error al crear la solicitud");
  }

   
  } catch (error) {
    console.error("Error en la solicitud");
  }
}

function calcularTotal() {
  const tabla = document.getElementById('table2');
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

document.addEventListener("DOMContentLoaded", cargarProveedores);

combobox.addEventListener("change", cargarProductos);
document.querySelector("#createPurchase").addEventListener("click",crearCompra)
