const searchBar = document.getElementById('search-bar');
const idSucursal = 1 //que se cargue del localstorage
const resultsContainer = document.getElementById('results');
let productos = [];

document.addEventListener("DOMContentLoaded", () => {
    resultsContainer.style.display = "none";

    const providersTableModal = document.getElementById("proveedoresModal");
    const tableBodyP = document.getElementById("table-body-proveedores");

    //ver proveedores
    providersTableModal.addEventListener("show.bs.modal", async function (event) {
        const res = await fetch("/api/proveedores");
        const providers = await res.json();
        console.log(providers);

        tableBodyP.innerHTML = "";

        providers.forEach((p) => {
            const tr = document.createElement("tr");

              tr.innerHTML = `
                      <tr>
                          <th scope="row">${p.idProveedor}</th>
                          <td>${p.razonSocial}</td>
                          <td>${p.contacto}</td>
                          <td>${p.rtnContacto}</td>
                          <td>${p.telefono}</td>
                          <td>${p.correo}</td>
                          <td>${p.direccion}</td>
                          <td>
                            <div id="admin-del-prov-buttons">
                                <button class="btn btn-danger btn-eliminar-proveedor" data-id=${p.idProveedor}>Eliminar</button>
                            </div>
                          </td>
                      </tr>
              `;
              tableBodyP.appendChild(tr);

              const userRol = user.rol.nombreRol.toLowerCase();
              console.log(userRol);
              if(userRol !== "administrador") {
                      const sectionAdminDP = document.getElementById("admin-del-prov-buttons");
                      sectionAdminDP.remove();
                  }
        });
    });



});

searchBar.addEventListener('keyup', async (e) => {
    const searchString = e.target.value.toLowerCase();
    if (searchString.length > 2) {
            const res = await fetch(`/api/productos/busqueda_simplificada?filtro=${searchString}`);
            productos = await res.json();
            displayProducts(productos);
            resultsContainer.style.display = "block";
            console.log(productos);
    } else {
        resultsContainer.style.display = "none";
    }
});

const displayProducts = (productos) => {
    if (!Array.isArray(productos)) {
        console.error("Respuesta inesperada:", productos);
        productos = [];
    }

    const ul = resultsContainer.querySelector("ul");
    ul.innerHTML = '';

    productos.forEach((p) => {
        const li = document.createElement("li");
        li.className = "list-group-item";
        li.textContent = p.nombreProducto;

        li.addEventListener("click", () => {
            window.location.href = `/detalles?idProducto=${p.idProducto}&idSucursal=${idSucursal}`;
        });

        ul.appendChild(li);
    });
};

/*
if(userRol !== "administrador") {
    const sectionAdminDP = document.getElementById("admin-del-prov-buttons");
    sectionAdminDP.remove();
}*/

document.addEventListener("click", async (e) => {
  if (e.target.classList.contains("btn-eliminar-proveedor")) {
    const idProveedor = e.target.dataset.id;

    const confirmar = confirm("¿Está seguro de que desea eliminar este proveedor?");
    if (!confirmar) return;

    try {
      const response = await fetch(`/api/proveedores/${idProveedor}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("Proveedor eliminado con éxito.");
        e.target.closest("tr").remove();
      } else {
        alert("Este proveedor tiene productos activos con nosotros, no se puede eliminar");
      }
    } catch (error) {
      console.error("Error al eliminar el proveedor:", error);
      alert("Error al eliminar el proveedor.");
    }
  }
});