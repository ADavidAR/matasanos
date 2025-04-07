(function () {

    document.querySelector("#add-form").addEventListener("submit", async function (event) {
        event.preventDefault();

        const form = event.target;
        const msg = document.querySelector("#msg-role-add");
        const input = document.querySelector("#role-name-add");
        if(!form.checkValidity()) {
            event.stopPropagation();

            msg.textContent = "Ingrese el nombre del rol"
        }

        form.classList.add("was-validated");
        
        fetch(`/api/roles/agregar/${encodeURIComponent(input.value)}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        }).then(r => r.json())
        .then(pl => {

            if(!pl) {
                msg.textContent = "El rol ya existe";
    
                input.classList.add("is-invalid");
                return;
            }
        })


        window.location.reload();
    })

    document.querySelector("#edit-form").addEventListener("submit", async function (event) {
        event.preventDefault();

        const form = event.target;
        const msg = document.querySelector("#msg-role-edit");
        const input = document.querySelector("#role-name-edit");

        if(!form.checkValidity()) {
            event.stopPropagation();

            msg.textContent = "Ingrese el nombre del rol"
        }

        form.classList.add("was-validated");
        
        let success = await fetch(`/api/roles/verificar/existencia/${encodeURIComponent(input.value)}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        }).then(r => r.json())

        if(success) {
            msg.textContent = "El rol ya existe";
            input.classList.add("is-invalid");
            return;
        }

        await fetch(`/api/roles/modificar?nombreRol=${encodeURIComponent(input.value)}&idRol=${input.dataset.id}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"}
        });


        window.location.reload()

    })


    document.querySelector("#role-name-add").addEventListener("input", (event) => {
        const input = event.target;
        const msg = document.querySelector("#msg-role-add");

        if(input.validity.valid) {

            msg.textContent = "Ingrese el nombre del rol";
            input.classList.remove("is-invalid");
        } else {
            input.classList.add("is-invalid");
        }
    })
    
    document.querySelector("#role-name-edit").addEventListener("input", (event) => {
        const input = event.target;
        const msg = document.querySelector("#msg-role-add");
        

        if(input.validity.valid) {

             
            document.querySelector("#edit-submit-btn").disabled = input.value === input.dataset.name;
            msg.textContent = "Ingrese el nombre del rol";
            input.classList.remove("is-invalid");
        } else {
            input.classList.add("is-invalid");
        }
    })
  })()

window.addEventListener("DOMContentLoaded", async () => {
    const optionsNav = document.querySelector("#nav-options");
    const rolesSection = document.querySelector("#roles-container");
    const roleH5 = document.querySelector("#role");
    
    roleH5.textContent = userData.rol.nombreRol;
    //Add role form after-hidden reset
    document.querySelector("#add-role-modal").addEventListener("hidden.bs.modal", (event) => {
        const input = document.querySelector("#role-name-add");
        
        input.value = "";
        input.dataset.name = "";
    })
    
    //Edit form after-hidden reset
    document.querySelector("#edit-role-modal").addEventListener("hidden.bs.modal", (event) => {
        const input = document.querySelector("#role-name-edit");

        input.value = "";
        input.dataset.name = "";

        document.querySelector("#edit-submit-btn").disabled = true;

    })

    let roles = await fetch("/api/roles").then(r => r.json());


    // roles =  [
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

    const rolesSect = document.querySelector("#roles-container")
    let modifyDisplay;
    let createDisplay;
    let delDisplay;

    if(auth.mod) {
        modifyDisplay = "flex";
        rolesSect.classList.add("enable");
    } else {
        modifyDisplay = "none";
        rolesSect.classList.add("disable");
    }

    createDisplay = (auth.create) ? "block" : "none";
    delDisplay =(auth.del) ? "block" : "none";

    userData.rol.permisos.forEach((p) => {
        if(p.acceso && p.permiso.accesoDirecto) {
            const option = document.createElement("a");
            option.classList.add("nav");
            option.textContent = p.permiso.descripcion;
            option.dataset.id = p.permiso.idPermiso;

            option.href = p.permiso.endpointUrl;
            optionsNav.appendChild(option);
        }
    })

    roles.forEach((r) => {
        const option = document.createElement("a");
        const spanOption = document.createElement("span");
        const spanBtn = document.createElement("span");

        spanOption.classList.add("role-btn-container");
        spanBtn.classList.add("btn-container");

        option.classList.add("btn");
        option.classList.add("btn-role");
        option.textContent = r.nombreRol;
        option.dataset.id = r.idRol;
        const btnEdit = document.createElement("i");
        const btnDel = document.createElement("i");

        btnEdit.setAttribute("class","bi bi-pencil-square btn btn-primary modify")
        btnEdit.setAttribute("data-bs-toggle", "modal");
        btnEdit.setAttribute("data-bs-target","#edit-role-modal");
        btnEdit.dataset.name = r.nombreRol
        btnEdit.dataset.id = r.idRol

        btnEdit.addEventListener("click", (event) => {
            const target = event.target;
            const input = document.querySelector("#role-name-edit");
            input.dataset.name = target.dataset.name;
            input.dataset.id = target.dataset.id;
            input.value = target.dataset.name;
        })

        btnDel.setAttribute("class","bi bi-trash btn btn-danger del modify")
        btnDel.dataset.id = r.idRol
        btnDel.dataset.name = r.nombreRol

        btnDel.addEventListener("click", async (event) => {
            const target = event.target;

            let response = await fetch(`/api/roles/verificar/eliminar/${target.dataset.id}`,{
                method: "POST",
                headers: {"Content-Type": "application/json"}
            }).then(r => r.json());

            if(response.error) {
                const errorModal = document.querySelector("#error-modal");
                let bsErrorModal = new bootstrap.Modal(errorModal);
                
                errorModal.querySelector(".modal-title").textContent = "Error: Rol en uso"
                errorModal.querySelector(".modal-body").textContent = response.msg;
                
                bsErrorModal.show();
                return;
            }

            const confirmModal = document.querySelector("#confirm-modal");
            let bsConfirmModal = new bootstrap.Modal(confirmModal);

            confirmModal.querySelector(".modal-title").textContent = "Confirmacion de Eliminación"
            confirmModal.querySelector(".modal-body").textContent = `¿Desea eliminar al rol '${target.dataset.name}'?`

            const btnConfirm = document.querySelector("#confirm-btn");
            btnConfirm.dataset.id = target.dataset.id;
            btnConfirm.dataset.name = target.dataset.name;

            bsConfirmModal.show();
        })
    

        option.addEventListener("click", (event) => {
            const anchor = event.target;
            let roleData = {
                idRol: parseInt(anchor.dataset.id),
                nombreRol: anchor.textContent
            };
            localStorage.setItem("selectedRole", JSON.stringify(roleData));
            
            window.location.href = "/permisos"
        })

        spanBtn.appendChild(btnEdit);
        spanBtn.appendChild(btnDel);

        spanOption.appendChild(option);
        spanOption.appendChild(spanBtn);
        rolesSection.appendChild(spanOption);
    })

    document.querySelector("#logout-btn").addEventListener("click", () => {
        window.localStorage.removeItem("userData");
        window.location.href = "/login";
    })

    document.querySelector("#confirm-btn").addEventListener("click", async (event) => {
        const btn = event.target;

        await fetch(`/api/roles/eliminar/${btn.dataset.id}`, {
            method: "DELETE",
            headers: {"Content-Type": "application/json"}
        });

        window.location.reload();
    })

    
    document.querySelectorAll(".del").forEach( (el) => {
        el.style.display = delDisplay;
    })
    
    document.querySelectorAll(".create").forEach( (el) => {
        el.style.display = createDisplay;
    })
    
    document.querySelectorAll(".modify").forEach( (el) => {
        el.style.display = modifyDisplay;
    })

})