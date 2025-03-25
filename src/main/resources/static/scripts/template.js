window.addEventListener("DOMContentLoaded", async () => {
    const roleH1 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    
    let userData = JSON.parse(localStorage.getItem("userData"));


    roleH1.textContent = userData.rol.nombreRol;
    
    userData.rol.permisos.forEach((p) => {
        if(p.permiso.accesoDirecto) {
            const option = document.createElement("a");
            option.classList.add("btn");
            option.textContent = p.permiso.descripcion;
            option.dataset.id = p.permiso.idPermiso;

            option.href = p.permiso.endpointUrl;
            optionsNav.appendChild(option);
        }
    })
})