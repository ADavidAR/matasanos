window.addEventListener("DOMContentLoaded", async () => {
    let idSucursal = 1; //que lo obbtenga de localstorage
    const params = new URLSearchParams(window.location.search);
    const idDepartment = params.get("idDepartamento");
    const idCategory = params.get("idCategoria");

    const response = await fetch(`/api/productos/sucursal/${idSucursal}?idCategoria=${idCategory}`);
    const products = await response.json();
    console.log(products);

    const section = document.querySelector("#products-container");
    section.innerHTML = "";

    //carga los productos
    products.forEach(p => {
        const column = document.createElement("div");
        column.classList.add("col");
        column.innerHTML = `

               <div data-id="${p.idProducto}" data-cat="${p.categoria.idCategoria}" data-dept="${p.categoria.departamento.idDepartamento}" class="card card-products">
                  <div class="card-body">
                      <h5 class="card-title text-center">${p.nombreProducto}</h5>
                   </div>
                </div>
      `;
        section.appendChild(column);

        const card = column.querySelector(".card");
        card.addEventListener("click", () => {
            window.location.href = `./productos?idCategoria=${p.idCategoria}&idDepartamento=${idDepartment}`;
        })
    })
})