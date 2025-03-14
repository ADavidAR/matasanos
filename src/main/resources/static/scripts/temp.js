window.addEventListener("DOMContentLoaded", async () => {
    const roleH1 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    
    let datosUsuario = JSON.parse(localStorage.getItem("datos"));

    roleH1.textContent = datosUsuario.nombreRol;
    
    
    datosUsuario.permisos.forEach((p) => {
        console.log(p);
        const option = document.createElement("a");
        option.classList.add("nav");
        option.textContent = p.descripcion;
        option.dataset.id = p.idpermiso;
        optionsNav.appendChild(option);
    })
})