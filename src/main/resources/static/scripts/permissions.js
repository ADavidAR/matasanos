const userPermissions = new Map();

let changeCount = 0;

function checkIfChanged(event) {
    const checkbox = event.target;
    const initialState = checkbox.getAttribute('data-initial') === 'true';

    if (checkbox.checked !== initialState) {
        if (!checkbox.checked) {
            changeCount--;

        } else {
            changeCount++;
        }

        checkbox.setAttribute('data-initial', checkbox.checked);
  
        button.disabled = changeCount === 0;
    }
}

window.addEventListener("DOMContentLoaded", async () => {
    const roleH1 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    const permissionTable = document.querySelector("#permission-table");
    const permissionTBody = document.querySelector("#permission-tbody");

    permissionTable.classList.add("enable");

    if(localStorage.getItem("userData") === null)
        window.location.href = "/login";

    let userData = JSON.parse(localStorage.getItem("userData"));

    let access = false;
    let mod = false;
    for(const p of userData.rol.permisos ) {
        if(p.permiso.pantallahtml === "permissions.html") {
            access = true;
            mod = p.modificacion;
            break;
        }
    }

    if(!access) window.location.href = "/home"
    
    let selectedRole = JSON.parse(localStorage.getItem("selectedRole"));

    // if(localStorage.getItem("selectedRole") === null) {
    //     window.location.href = "/Permisos";
    // }

    let permissions = await fetch("/api/permisos/rol", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(userData.rol)
    }).then(r => r.json())

    permissions.forEach(p => {
        const tr = document.createElement("tr");

        tr.dataset.id = p.idrolpermiso
        const tdDesc = document.createElement("td");

        tdDesc.textContent = p.permiso.descripcion

        tdDesc.classList.add("desc");
        tr.appendChild(tdDesc);


        const tdValues = [
            {
                name: "create",
                value: p.creacion
            }, 
            {
                name:"modify",
                value: p.modificacion
            }, 
            {
                name:"del",
                value: p.eliminacion
            }
        ];

        tdValues.forEach( v => {
            const tdCheckbox = document.createElement("td");
            const inputCheckbox = document.createElement("input");

            inputCheckbox.type = "checkbox";
            inputCheckbox.name = v.name;
            inputCheckbox.checked = v.value;

            tdCheckbox.appendChild(inputCheckbox);

            tr.appendChild(tdCheckbox);
        })

        permissionTBody.appendChild(tr);


    });
    const modifyElems = document.querySelectorAll(".modify");

    
    
    modifyElems.forEach( (el) => {
        el.style.display = "none";
    })

    
    roleH1.textContent = userData.rol.nombrerol;
    
    userData.rol.permisos.forEach((p) => {
        const option = document.createElement("a");
        option.classList.add("btn");
        option.textContent = p.permiso.descripcion;
        option.dataset.id = p.permiso.idpermiso;
        optionsNav.appendChild(option);
    })
})