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
      costo.classList.add = "cost";
      let cantidad = document.createElement("input");
      cantidad.type = "number";
      cantidad.classList.add = "quantity form-control input-number";
      cantidad.value = "1";
      cantidad.min = "1";
      let sacar = document.createElement("button");
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
  }
}

async function cargarProveedores() {
  try {
    let response = await fetch("/api/compra/cargar");
    let data = await response.json();
    console.log(data);
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
//agregar productos a la myTable
async function cargarProductos() {
  let selecion = combobox.options[combobox.selectedIndex];
  const tabla = document.querySelector("#myTable tbody");
  tabla.innerHTML = "";
  try {
    let response = await fetch(
      `/api/compra/cargarProductos/${selecion.dataset.supplierId}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );
    let data = await response.json();
    console.log(data);

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

document.addEventListener("DOMContentLoaded", cargarProveedores);
combobox.addEventListener("change", cargarProductos);
