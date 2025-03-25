window.addEventListener("DOMContentLoaded", async () => {
    const optionsNav = document.querySelector("#nav-options");
    const rolesSection = document.querySelector("#roles-container");
    
    let userData = JSON.parse(localStorage.getItem("userData"));

    let roles = await fetch("/api/roles", {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(r => r.json());

    

    // roles = {
    //     roles: [
    //         {
    //             nombreRol: "Rol1",
    //             idRol: 1
    //         },
    //         {
    //             nombreRol: "Rol2",
    //             idRol: 2
    //         },
    //         {
    //             nombreRol: "Rol3",
    //             idRol: 3
    //         }
    //     ]
    // }

    userData.rol.permisos.forEach((p) => {
        if(p.permiso.accesoDirecto) {
            const option = document.createElement("a");
            option.classList.add("nav");
            option.textContent = p.permiso.descripcion;
            option.dataset.id = p.permiso.idPermiso;

            option.href = p.permiso.endpoint;
            optionsNav.appendChild(option);
        }
    })

    console.log(roles);

    roles.forEach((r) => {
        const option = document.createElement("a");
        option.classList.add("btn");
        option.classList.add("btn-role");
        option.textContent = r.nombreRol;
        option.dataset.id = r.idRol;
        option.addEventListener("click", (event) => {
            const anchor = event.target;
            let roleData = {
                idRol: anchor.dataset.id,
                nombreRol: anchor.textContent
            };
            localStorage.setItem("selectedRole", JSON.stringify(roleData));

            window.location.href = "/permisos"
        })
        rolesSection.appendChild(option);
    })
})