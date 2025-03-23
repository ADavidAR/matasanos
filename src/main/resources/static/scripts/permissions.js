window.addEventListener("DOMContentLoaded", async () => {
    const roleH1 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    const permissionTable = document.querySelector("#permission-table");

    permissionTable.classList.add("enable");
    let userData = JSON.parse(localStorage.getItem("userData"));

    const modifyElems = document.querySelectorAll(".modify");

    modifyElems.forEach( (el) => {
        el.style.display = "none";
    })

    
    roleH1.textContent = userData.nombreRol;
    
    userData.permisos.forEach((p) => {
        const option = document.createElement("a");
        option.classList.add("nav");
        option.textContent = p.descripcion;
        option.dataset.id = p.idpermiso;
        optionsNav.appendChild(option);
    })
})