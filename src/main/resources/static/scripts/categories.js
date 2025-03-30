window.addEventListener("DOMContentLoaded", async () => {
    const params = new URLSearchParams(window.location.search);
    const idDepartment = params.get("id");

    const response = await fetch(`/api/categorias/${idDepartment}`);
    const categories = await response.json();
    console.log(categories);

    document.getElementById("view-title").textContent = `${categories[0].departamento.nombreDepartamento}`;

    const section = document.querySelector("#categories-container");
    section.innerHTML = "";

    //carga las categorias
    categories.forEach(p => {
        const column = document.createElement("div");
        column.classList.add("col");
        column.innerHTML = `

               <div data-id="${p.idCategoria}" class="card card-categories">
                  <div class="card-body">
                      <h5 class="card-title text-center">${p.nombreCategoria}</h5>
                   </div>
                </div>
      `;
        section.appendChild(column);

        const card = column.querySelector(".card");
        card.addEventListener("click", () => {
            window.location.href = `/productos?idCategoria=${p.idCategoria}&idDepartamento=${idDepartment}`;
        })
    })
})