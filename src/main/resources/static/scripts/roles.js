window.addEventListener("DOMContentLoaded", async () => {
    const optionsNav = document.querySelector("#nav-options");
    const rolesSection = document.querySelector("#roles-container");
    
    let userData = JSON.parse(localStorage.getItem("userData"));

    let roles //= await fetch("/api/roles", {
    //     method: "GET",
    //     headers: {
    //         "Content-Type": "application/json"
    //     }
    // }).then(r => r.json);

    console.log(localStorage.getItem("userData"))

    roles = {
        roles: [
            {
                nombreRol: "Rol1",
                idRol: 1
            },
            {
                nombreRol: "Rol2",
                idRol: 2
            },
            {
                nombreRol: "Rol3",
                idRol: 3
            }
        ]
    }

    // userData.rol.permisos.forEach((p) => {
    //     if(p.permiso.accesoDirecto) {
    //         const option = document.createElement("a");
    //         option.classList.add("btn");
    //         option.textContent = p.permiso.descripcion;
    //         option.dataset.id = p.permiso.idPermiso;

    //         option.href = p.permiso.pantallaHtml;
    //         optionsNav.appendChild(option);
    //     }
    // })

    roles.roles.forEach((r) => {
        const option = document.createElement("a");
        option.classList.add("btn");
        option.classList.add("btn-role");
        option.textContent = r.nombreRol;
        option.dataset.id = r.idrol;
        rolesSection.appendChild(option);
    })
})