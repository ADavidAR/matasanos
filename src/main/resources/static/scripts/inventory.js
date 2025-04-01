window.addEventListener("DOMContentLoaded", async () => {
    const response = await fetch("/api/departamentos");
    const departments = await response.json();
    console.log(departments);
    const section = document.querySelector("#departments-container");
    section.innerHTML = "";

    //carga los departamentos
    departments.forEach(p => {
        const column = document.createElement("div");
        column.classList.add("col");
        column.innerHTML = `

               <div data-id="${p.idDepartamento}" class="card card-department">
                  <div class="card-body">
                      <h5 class="card-title text-center">${p.nombreDepartamento}</h5>
                   </div>
                </div>
      `;
        section.appendChild(column);

        const card = column.querySelector(".card");
        card.addEventListener("click", () => {
            window.location.href = `/categorias?id=${p.idDepartamento}`;
        })
    })
})