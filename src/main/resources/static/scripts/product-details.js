window.addEventListener("DOMContentLoaded", async () => {
    let idSucursal = 1; //que lo obbtenga de localstorage
    const params = new URLSearchParams(window.location.search);
    const idProduct = params.get("idProducto");

    const response = await fetch(`/api/productos/${idProduct}?idSucursal=${idSucursal}`);
    const details = await response.json();
    console.log(details);

    document.getElementById("view-title").textContent = `${details.nombreProducto}`;

    const section = document.querySelector("#detail-container");
    section.innerHTML = "";

    //carga los detalles
    const column = document.createElement("div");
    column.classList.add("card");
    column.classList.add("card-details");
    column.innerHTML = `

          <div class="card-header">
            <h2 id="productName">${details.nombreProducto}</h2>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Inventario: ${details.inventario}</li>
            <li class="list-group-item">Producto ID: ${details.idProducto}</li>
            <li class="list-group-item">Descripción: ${details.descripcion}</li>
            <li class="list-group-item">Precio de venta: ${details.precioVenta}</li>
            <li class="list-group-item">Fecha de Vencimiento: ${details.fechaVencimiento}</li>
            <li class="list-group-item" id="productRestriction"></li>
            <li class="list-group-item">Categoria: ${details.categoria.nombreCategoria}</li>
            <li class="list-group-item">Departamento: ${details.categoria.departamento.nombreDepartamento}</li>
          </ul>
      `;
      section.appendChild(column);

      let productRest = document.getElementById("productRestriction");

      if (details.ventaLibre === true) {
          productRest.textContent = "Producto de venta libre";
      } else {
          productRest.textContent = "No es un producto de venta libre";
      }
})