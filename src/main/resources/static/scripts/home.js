window.addEventListener("DOMContentLoaded", async () => {
    const roleH1 = document.querySelector("#role");
    const optionsNav = document.querySelector("#options");
    
    let userData = JSON.parse(localStorage.getItem("userData"));
    
    roleH1.textContent = userData.rol.nombrerol;
    
    userData.rol.permisos.forEach((p) => {
        const option = document.createElement("a");
        option.classList.add("btn");
        option.textContent = p.permiso.descripcion;
        option.dataset.id = p.permiso.idpermiso;
        optionsNav.appendChild(option);
    })
})