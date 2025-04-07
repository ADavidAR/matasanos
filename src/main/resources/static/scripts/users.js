const userSelect = document.querySelector("#select-user");

window.addEventListener("DOMContentLoaded", async () => {
    const roleH5 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    const editBtn = document.querySelector("#edit-btn");
    const cancelBtn = document.querySelector("#cancel-btn-modify");
    const delBtn = document.querySelector("#del-btn");
    
    document.querySelector("#logout-btn").addEventListener("click", () => {
        window.localStorage.removeItem("userData");
        window.location.href = "/login";
    })
    
    // Edit form
    const username = document.querySelector("#username");
    const changePass = document.querySelector("#change-password");

    const roleSelect = document.querySelector("#role-select-edit");
    const activeCheckbox = document.querySelector("#active-checkbox");
    const employeeSelect =document.querySelector("#employee-select-modify");

    const passwordEdit = document.querySelector("#password");
    const passwordConfirmEdit = document.querySelector("#password-confirm");
    const feedbackEdit = document.querySelector("#password-feedback-modify");
    
    const passwordAdd = document.querySelector("#password-add");
    const passwordConfirmAdd = document.querySelector("#password-confirm-add");
    const feedbackAdd = document.querySelector("#password-feedback-add");
    
    roleH5.textContent = userData.rol.nombreRol;

    let users = await fetch("/api/usuarios").then(r => r.json());
    let roles = await fetch("/api/roles").then(r => r.json());
    let employees = await fetch("/api/empleados/sinusuario").then(r => r.json())

    console.log(employees);

    users.forEach( u => {
        const option = document.createElement("option");
        option.value = u.idUsuario;
        option.textContent = u.usuario;
        userSelect.appendChild(option);
    })

    roles.forEach(r => {
        const optionEdit = document.createElement("option");
        optionEdit.value = r.idRol;
        optionEdit.textContent = r.nombreRol;
        document.querySelector("#role-select-edit").appendChild(optionEdit);
        
        const optionAdd = document.createElement("option");
        optionAdd.value = r.idRol;
        optionAdd.textContent = r.nombreRol;
        document.querySelector("#role-select-add").appendChild(optionAdd);
    });
    
    employees.forEach(e => {
        const optionEdit = document.createElement("option");
        optionEdit.value = e.idEmpleado;
        optionEdit.textContent = `${e.persona.primerNombre} ${e.persona.segundoNombreNombre ? `${e.persona.segundoNombreNombre} `: ""}${e.persona.primerApellido}${e.persona.segundoApellido ? ` ${ e.persona.segundoApellido}` : ""} - ${e.persona.dni}`
;
        document.querySelector("#employee-select-modify").appendChild(optionEdit);
        
        const optionAdd = document.createElement("option");
        optionAdd.value = e.idEmpleado;
        optionAdd.textContent = e.nombreEmpleadol;
        document.querySelector("#employee-select-add").appendChild(optionAdd);
    });

    
    userSelect.addEventListener("change", setupReadOnlyMode)
    editBtn.addEventListener("click", setupEditMode)
    cancelBtn.addEventListener("click", setupReadOnlyMode)

    username.addEventListener("input", checkChanges);
    roleSelect.addEventListener("change", checkChanges);
    employeeSelect.addEventListener("change", checkChanges);
    activeCheckbox.addEventListener("change", checkChanges);

    // modalAddUser.addEventListener("show.bs.modal", () => {
    //     modalAddUser.removeAttribute("inert");
    // });
    
    // modalAddUser.addEventListener("hidden.bs.modal", () => {
    //     modalAddUser.setAttribute("inert", "");
    // });

    document.querySelector("#add-user-modal").addEventListener("hidden.bs.modal", clearAddModal);
    document.querySelector("#reset-btn").addEventListener("click", clearAddModal);
    

    changePass.addEventListener("change", event => {
        const check = event.target;
        const passwordDivs = document.querySelectorAll("#password-modify-div, #password-confirm-modify-div")
        if(check.checked) {
            passwordDivs.forEach( div => {
                document.querySelector("#submit-btn-modify").disabled = true;
                const input = div.querySelector("input");
                input.disabled = false;
                input.required = true
                div.querySelector("button").classList.remove("d-none");
            })
            return;
        }

        passwordDivs.forEach( div => {
            const input = div.querySelector("input");
            input.disabled = true;
            input.value = "";
            input.required = false
            div.querySelector("button").classList.add("d-none");
        })

        checkChanges()

    })

    passwordAdd.addEventListener("input", e => {
        document.querySelector("#submit-btn-add").disabled = !validatePassword(passwordAdd, passwordConfirmAdd, feedbackAdd); 
    })
    
    passwordConfirmAdd.addEventListener("input", e => {
        document.querySelector("#submit-btn-add").disabled = !validatePassword(passwordAdd, passwordConfirmAdd, feedbackAdd); 
    })
    
    passwordEdit.addEventListener("input", e => {
        document.querySelector("#submit-btn-modify").disabled = !validatePassword(passwordEdit, passwordConfirmEdit, feedbackEdit); 
    })

    passwordConfirmEdit.addEventListener("input", e => {
        document.querySelector("#submit-btn-modify").disabled = !validatePassword(passwordEdit, passwordConfirmEdit, feedbackEdit); 
    })

    delBtn.addEventListener("click", async (event) => {
        try {
            const response = await fetch(`/api/usuarios/verificar/eliminar/${userSelect.value}`);
    
            const data = await response.json();

            if(!response.ok) {
                throw new Error(data.msg);
            }
    
            Swal.fire({
                title: 'Confirmacion de Eliminacion',
                text: `¿Desea eliminar al usuario: ${userSelect.querySelector(`option[value='${userSelect.value}']`).textContent}?`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#dc3545',
                cancelButtonColor: '#51585e',
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar'
            }).then(async (result) => {
                if (result.isConfirmed) {

                    await fetch(`/api/usuarios/eliminar/${userSelect.value}`, {
                        method:"DELETE"
                    })

                    Swal.fire({
                        icon: 'success',
                        title: '¡Éxito!',
                        text: 'Usuario eliminado exitosamente.',
                        confirmButtonColor: '#14A44d',
                        confirmButtonText: 'Aceptar'
                    }).then(() => {
                        window.location.reload();
                    });
                }
            });
        } catch (error) {
            if (error.message.includes('usuario en uso')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Usuario en uso',
                    text: 'El usuario ha realizado cambios en otras secciones y no puede ser eliminado en uso.',
                    confirmButtonText: 'Entendido'
                });
            } else if (error.message.includes('usuario inexistente')){
                Swal.fire({
                    icon: 'error',
                    title: 'Usuario inexistente',
                    text: 'El no existe un usuario con ese nombre',
                    confirmButtonText: 'Entendido'
                });
            }
        }
       
    })

    document.querySelectorAll(".del").forEach( el => {
        if(!auth.del) el.remove();
    })

    document.querySelectorAll(".modify, .del").forEach( el => {
        if(!auth.mod) el.remove();
    })
    
    document.querySelectorAll(".create").forEach( el => {
        if(!auth.create) el.remove();
    })

    document.querySelector("#user-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        
        const submitBtn = document.querySelector("#submit-btn-modify");
        submitBtn.disabled = true;

        let changePassword = document.querySelector("#change-password").checked;

        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";
        
        try {
            
                                                 
            const formData = {
                idUsuario: userSelect.value,
                usuario: document.querySelector("#username").value,
                rol: {
                    idRol: parseInt(document.querySelector("#role-select-edit").value),
                },
                empleado: {
                    idEmpleado: document.querySelector("#employee-select-modify").value || 0,
                },
                contrasena: changePassword ?  document.querySelector("#password-confirm").value : null,
                activo: document.querySelector("#active-checkbox").checked,
                idUsuarioModificacion: userData.idUsuario
            };

            const response = await fetch("/api/usuarios/verificar/edicion", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            const data = await response.json()
            
            if (!response.ok) {
                throw new Error(data.msg || 'Error en la solicitud');
            }

            await fetch("/api/usuarios/modificar", {
                method:"POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            })
            
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Usuario modificado correctamente',
                confirmButtonColor: '#14A44d',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                window.location.reload();
            });
            
        } catch (error) {
            if (error.message.includes('usuario existente')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Usuario existente',
                    text: 'El nombre de usuario ya está en uso. Por favor elija otro.',
                    confirmButtonText: 'Entendido'
                });
                document.getElementById('username').focus();
            } else if (error.message.includes('empleado con usuario')) {
                Swal.fire({
                    icon: "error",
                    title: "Usuario ya asignado",
                    text: "Este empleado ya tiene un usuario asociado.",
                    confirmButtonText: "Entendido"
                });
                document.querySelector("#employee-select-modify").focus();
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: error.message || "Ocurrió un error al procesar la solicitud",
                    confirmButtonText: "Entendido"
                });
            }
        } finally {
            submitBtn.disabled = false;
            submitBtn.innerHTML = '<i class="fas fa-save me-1"></i> Guardar Cambios';
        }
    });

    document.querySelector("#user-form-add").addEventListener("submit", async function(event) {
        event.preventDefault();
        const submitBtn = document.querySelector("#submit-btn-add");
        submitBtn.disabled = true;
        
        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";

        try {
            const formData = {
                usuario: document.querySelector("#username-add").value,
                contrasena: document.querySelector("#password-add").value,
                activo: document.querySelector("#active-checkbox-add").checked,
                rol: {
                    idRol: parseInt(document.querySelector("#role-select-add").value),
                },
                empleado: {
                    idEmpleado: document.querySelector("#employee-select-add").value || 0,
                },
                idUsuarioCreacion: userData.idUsuario
            };

            const response = await fetch("/api/usuarios/verificar/crear", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            const data = await response.json();
            
            if (!response.ok) {
                throw new Error(data.msg || data.message || 'Error en la solicitud');
            }

            await fetch("/api/usuarios/agregar", {
                method:"POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            })
            
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Usuario registrado correctamente',
                confirmButtonColor: '#14A44d',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                window.location.reload();
            });
            
        } catch (error) {
            if (error.message.includes('usuario existente')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Usuario existente',
                    text: 'El nombre de usuario ya está en uso. Por favor elija otro.',
                    confirmButtonText: 'Entendido'
                });
                document.getElementById('username').focus();
            } else if (error.message.includes('empleado con usuario')) {
                Swal.fire({
                    icon: "error",
                    title: "Usuario ya asignado",
                    text: "Este empleado ya tiene un usuario asociado.",
                    confirmButtonText: "Entendido"
                });
                document.querySelector("#employee-select-modify").focus();
            } else {
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: error.message || "Ocurrió un error al procesar la solicitud",
                    confirmButtonText: "Entendido"
                });
            }
        } finally {
            submitBtn.disabled = false;
            submitBtn.innerHTML = '<i class="fas fa-save me-1"></i> Guardar Cambios';
        }
    });

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
})


function showPassword(btn) {
    const input = btn.previousSibling.previousSibling;
    const btnIcon = btn.querySelector("i");
    if (input.type === "password") {
        input.type = "text";
        btnIcon.classList.remove("fa-eye");
        btnIcon.classList.add("fa-eye-slash");
    } else {
        input.type = "password";
        btnIcon.classList.remove("fa-eye-slash");
        btnIcon.classList.add("fa-eye");
    }    
}


async function loadUserData() {
    
    const editBtn = document.querySelector("#edit-btn");
    const delBtn = document.querySelector("#del-btn");
    const employeeName = document.querySelector("#employee-name");
    const idEmployee = document.querySelector("#id-number");
    const selectEmployee =document.querySelector("#employee-select-modify");
    document.querySelector("#submit-btn-modify").disabled = true;
    if(userSelect.value !== "") {
        changesOnUser = false;
        editBtn?.classList.remove("d-none");
        delBtn?.classList.remove("d-none");
        
        let selectedUserData = await fetch(`/api/usuarios/${userSelect.value}`).then(r => r.json());
        
        console.log(selectedUserData);

        //user data

        //username
        const username = document.querySelector("#username");
        username.value = selectedUserData.usuario;
        username.dataset.value = selectedUserData.usuario;

        //role
        const roleName = document.querySelector("#role-name");
        roleName.value = selectedUserData.rol.nombreRol;

        const roleSelect = document.querySelector("#role-select-edit");
        roleSelect.value = selectedUserData.rol.idRol;
        roleSelect.dataset.value = selectedUserData.rol.idRol;

        //active
        const active = document.querySelector("#active-checkbox");
        active.checked = selectedUserData.activo;
        active.dataset.value = selectedUserData.activo;


        //wmployee data
        //check if user is asigned to an employee
        if(selectedUserData.empleado.idEmpleado === 0) {
            document.querySelector("#without-employee").classList.remove("d-none");
            employeeName.parentElement.classList.add("d-none")
            idEmployee.parentElement.classList.add("d-none")

            employeeName.value = ""
            idEmployee.value = "";

            return;
        }

        document.querySelector("#without-employee").classList.add("d-none")

        //name
        employeeName.parentElement.classList.remove("d-none")
        employeeName.value = `${selectedUserData.empleado.persona.primerNombre} ${selectedUserData.empleado.persona.segundoNombreNombre ? `${selectedUserData.empleado.persona.segundoNombreNombre} `: ""}${selectedUserData.empleado.persona.primerApellido}${selectedUserData.empleado.persona.segundoApellido ? ` ${ selectedUserData.empleado.persona.segundoApellido}` : ""}`

        //id
        idEmployee.parentElement.classList.remove("d-none")
        idEmployee.value = selectedUserData.empleado.persona.dni;

        //select employee
        selectEmployee.querySelector(`option[value="${selectEmployee.dataset.value}"]`)?.remove();
        selectEmployee.innerHTML += `<option value="${selectedUserData.empleado.idEmpleado}">${selectedUserData.empleado.persona.primerNombre} ${selectedUserData.empleado.persona.segundoNombreNombre ? `${selectedUserData.empleado.persona.segundoNombreNombre} `: ""}${selectedUserData.empleado.persona.primerApellido}${selectedUserData.empleado.persona.segundoApellido ? ` ${ selectedUserData.empleado.persona.segundoApellido}` : ""} ${selectedUserData.empleado.persona.dni}</option>`
        selectEmployee.value = selectedUserData.empleado.idEmpleado;
        selectEmployee.dataset.value = selectedUserData.empleado.idEmpleado;
                        
        return;
    }

    editBtn?.classList.add("d-none")
    delBtn?.classList.add("d-none")

    changesOnUser = false;        

    //user data

    //username
    const username = document.querySelector("#username");
    username.value = "";
    username.dataset.value = "";

    //role
    const roleName = document.querySelector("#role-name");
    roleName.value = "";

    const roleSelect = document.querySelector("#role-select-edit");
    roleSelect.value = "";
    roleSelect.dataset.value = "";

    //active
    const active = document.querySelector("#active-checkbox");
    active.checked = false;
    active.dataset.value = "";
    

    //wmployee data

    document.querySelector("#without-employee").classList.add("d-none")

    //name
    employeeName.parentElement.classList.remove("d-none")
    employeeName.value = ""

    //id
    idEmployee.parentElement.classList.remove("d-none")
    idEmployee.value = "";

    //select input
    selectEmployee.querySelector(`option[value="${selectEmployee.dataset.value}"]`).remove();
    selectEmployee.value = "";
    selectEmployee.dataset.value = "";
}

function checkChanges() {

    if(document.querySelector("#change-password").checked) return;
    const inputs = document.querySelectorAll("#username, #role-select-edit, #active-checkbox, #employee-select-modify");

    for(const i of inputs) {

        let changed =  i.type === "checkbox" ? (i.dataset.value === "true") !== i.checked : i.value !== i.dataset.value
        if(changed) {
            document.querySelector("#submit-btn-modify").disabled = false;
            return;
        }
    }
    document.querySelector("#submit-btn-modify").disabled = true;
}

function setupEditMode() {

    document.querySelector("#edit-btn")?.classList.add("d-none");
    document.querySelector("#del-btn")?.classList.add("d-none");

    //user data input setup
    
    //username
    const username = document.querySelector("#username");
    username.disabled = false;
    username.parentElement.classList.add("col-md-6")

    //pasword
    document.querySelector("#change-password").parentElement.classList.remove("d-none")
    document.querySelector("#password-modify-div").classList.remove("d-none");
    
    //password confirmation
    document.querySelector("#password-confirm-modify-div").classList.remove("d-none")

    //role
    const roleName = document.querySelector("#role-name");
    roleName.classList.add("d-none");
    roleName.parentElement.classList.add("col-md-6");
    document.querySelector("#role-select-edit").classList.remove("d-none")

    //active
    document.querySelector("#active-checkbox").disabled = false;
    
    
    //employee data
    document.querySelectorAll("#employee-data>.row").forEach( el => { el.classList.add("d-none"); })
    document.querySelector("#employee-select-container").classList.remove("d-none");

    //btn section activation
    const form = document.querySelector("#user-form");
    const btnDiv = form.querySelector("fieldset+div");
    btnDiv.classList.remove("d-none");
    btnDiv.classList.add("d-md-flex");
}

function setupReadOnlyMode() {
    loadUserData();

    //user data input setup
    
    //username
    const username = document.querySelector("#username");
    username.disabled = true;
    username.parentElement.classList.remove("col-md-6")

    //pasword
    document.querySelector("#change-password").parentElement.classList.add("d-none")
    document.querySelector("#password-modify-div").classList.add("d-none");
    
    //password confirmation
    document.querySelector("#password-confirm-modify-div").classList.add("d-none")

    //role
    const roleName = document.querySelector("#role-name");
    roleName.classList.remove("d-none");
    roleName.parentElement.classList.remove("col-md-6");
    document.querySelector("#role-select-edit").classList.add("d-none")

    //active
    document.querySelector("#active-checkbox").disabled = true;
    
    //employee data
    document.querySelector("#without-employee").classList.add("d-none");
    document.querySelectorAll("#employee-data>.row").forEach( el => { el.classList.remove("d-none"); })
    document.querySelector("#employee-select-container").classList.add("d-none");

    //btn section activation
    const form = document.querySelector("#user-form");
    const btnDiv = form.querySelector("fieldset+div");
    btnDiv.classList.add("d-none");
    btnDiv.classList.remove("d-md-flex");
}

function clearAddModal() {
    const inputs = document.querySelectorAll("#username-add, #role-select-add, #password-add, #password-confirm-add, #employee-select-add");
        inputs.forEach( input => input.value = "");

        document.querySelector("#active-checkbox-add").checked = true;
}

function validatePassword(password, confirmPassword, feedback) {
  if (password.value && confirmPassword.value) {
    if (password.value !== confirmPassword.value) {
      confirmPassword.classList.add('is-invalid');
      feedback.style.display = 'block';
      return false;
    } else {
      confirmPassword.classList.remove('is-invalid');
      feedback.style.display = 'none';
      return true;
    }
  }
  return false;
}