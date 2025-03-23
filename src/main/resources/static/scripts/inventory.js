window.addEventListener("DOMContentLoaded", async () => {
    const idSucursal = 1; //cambiarlo para que obtenga el id de localstorage
    const response = await fetch(`/api/productos?idSucursal=${idSucursal}`);
    const products = await response.json();
    console.log(products);
    const section = document.querySelector("#product-container");
    section.innerHTML = "";

products.forEach(p => {
    const column = document.createElement("div");
    column.classList.add("col-3", "mb-4");
    column.innerHTML = `
        <div class="col-3">
           <div data-id="${p.idproducto}" class="card">
              <div class="card-body">
                  <h5 class="card-title text-center">${p.nombre}</h5>
                   <p class="card-text">${p.descripcion}</p>
                   <p class="card-text">Inventario: ${p.inventario}</p>
                   <p class="card-text"><small class="text-muted">Producto ID: ${p.idproducto}</small></p>
               </div>
            </div>
        </div>

      `;
      section.appendChild(column);

      })

    const productDetail = document.getElementById("productDetail");
    const closeDetail = document.getElementById("closeDetail");

    section.addEventListener("click", async (i) => {
        const card = i.target.closest(".card");

        if (card) {
            const idProduct = card.getAttribute("data-id");

            try {
                const responseP = await fetch(`/api/productos/${idProduct}?idSucursal=${idSucursal}`);
                if(!responseP.ok) throw new Error("No se pudo obtener el producto");

                const productDetails = await responseP.json();
                console.log(productDetails);

                document.getElementById("productName").textContent = productDetails.nombre;
                document.getElementById("productDescription").textContent = productDetails.descripcion;
                document.getElementById("productPrice").textContent = `Precio: ${productDetails.precioventa}`;
                document.getElementById("productInventory").textContent = `Inventario: ${productDetails.inventario}`;
                document.getElementById("productCreationDate").textContent = `Fecha de creación: ${productDetails.fechacreacion}`;

                document.getElementById("product-container").style.display = "none";
                detail.style.display = "block";
            } catch (error) {
                console.error("Error al cargar producto: ", error);
            }
        }
    })

    closebtn.addEventListener("click", () => {
        detail.style.display = "none";
        document.getElementById("product-container").style.display = "flex";
    });
})