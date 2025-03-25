const userPermissions = new Map();

let changeCount = 0;
const saveChangesBtn = document.querySelector("#btn-save-changes");



function checkIfChanged(event) {
    const checkbox = event.target;
    const initialState = checkbox.getAttribute('data-initial') === 'true';

    changeCount += ( checkbox.checked === initialState ) ? 1 : -1;
    saveChangesBtn.disabled = changeCount === 0;
}

window.addEventListener("DOMContentLoaded", async () => {
    const optionsNav = document.querySelector("#nav-options");
    const permissionTable = document.querySelector("#permission-table");
    const permissionTBody = document.querySelector("#permission-tbody");

    let userData = JSON.parse(localStorage.getItem("userData"));

    let access = false;
    let mod = false;
    for(const p of userData.rol.permisos ) {
        if(p.permiso.endpoint === "permissions.html") {
            access = p.acceso;
            mod = p.modificacion;
            break;
        }
    }

    if(!access) window.location.href = "/home";
    
    const modifyElems = document.querySelectorAll(".modify");

    let modifyDisplay;

    if(mod) {
        modifyDisplay = "block";
        permissionTable.classList.add("enable");
    } else {
        modifyDisplay = "none";
        permissionTable.classList.add("disable");
    }

    saveChangesBtn.disabled = true;
    saveChangesBtn.addEventListener("click", async () => {
        const permissionTrs = document.querySelectorAll("#permission-tbody>tr");
        let rolePermissions = [];
        for(let t of permissionTrs) {

            let rp = {
                idRolPermiso: t.dataset.id
            };
            for(let i of t.querySelectorAll("input")) {
                rp[i.name] = i.checked
            }

            let tdDesc = t.querySelector("td.desc");

            rp.permiso = {
                idPermiso: tdDesc.dataset.id,
                descripcion: null,
                endpoint: null
            }

            rp.rol = JSON.parse(localStorage.getItem("selectedRole"))
            rolePermissions.push(rp)
        }
        
        let response = await fetch("/api/permisos/rol", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(rolePermissions)
        })

        if(response.ok) {
            window.location.reload();
        }
    })

    modifyElems.forEach( (el) => {
        el.style.display = modifyDisplay;
    })

    if(localStorage.getItem("selectedRole") === null) {
        window.location.href = "/roles";
    }
        
    let selectedRole = JSON.parse(localStorage.getItem("selectedRole"));
    
    
    let permissions = await fetch("/api/permisos/rol", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(selectedRole)
    }).then(r => r.json())

    permissions.forEach(p => {
        const tr = document.createElement("tr");

        tr.dataset.id = p.idRolPermiso
        const tdDesc = document.createElement("td");

        tdDesc.textContent = p.permiso.descripcion
        tdDesc.dataset.id = p.permiso.idPermiso

        tdDesc.classList.add("desc");
        tr.appendChild(tdDesc);

        const tdValues = [
            {
                name: "acceso",
                value: p.acceso
            }, 
            {
                name: "creacion",
                value: p.creacion
            }, 
            {
                name:"modificacion",
                value: p.modificacion
            }, 
            {
                name:"eliminacion",
                value: p.eliminacion
            }
        ];

        tdValues.forEach( v => {
            const tdCheckbox = document.createElement("td");
            const inputCheckbox = document.createElement("input");

            inputCheckbox.type = "checkbox";
            inputCheckbox.name = v.name;
            inputCheckbox.checked = v.value;
            inputCheckbox.addEventListener("change", checkIfChanged);

            tdCheckbox.appendChild(inputCheckbox);

            tr.appendChild(tdCheckbox);
        })

        permissionTBody.appendChild(tr);
    });
    
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
})