window.addEventListener("DOMContentLoaded", async () => {
    const roleH1 = document.querySelector("#title");
    const optionsNav = document.querySelector("#options");
    
    let userData = JSON.parse(localStorage.getItem("userData"));
    
    roleH1.textContent = userData.rol.nombreRol;
    console.log(userData)
    userData.rol.permisos.forEach((p) => {
        if(p.acceso && p.permiso.accesoDirecto) {
            const option = document.createElement("a");
            option.classList.add("btn");
            option.textContent = p.permiso.descripcion;
            option.dataset.id = p.permiso.idPermiso;

            option.href = p.permiso.endpointUrl;
            optionsNav.appendChild(option);
        }
    })
})