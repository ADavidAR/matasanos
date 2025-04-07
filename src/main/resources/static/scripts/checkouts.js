const branchSelect = document.querySelector("#select-branch");
const checkoutSelect = document.querySelector("#select-checkout");


window.addEventListener("DOMContentLoaded", async () => {
    const roleH5 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    const editBtn = document.querySelector("#edit-btn");
    const cancelBtn = document.querySelector("#cancel-btn-modify");
    const delBtn = document.querySelector("#del-btn");
  
    roleH5.textContent = userData.rol.nombreRol;

    let branches = await fetch("/api/sucursales").then(r => r.json());
    
    branches.forEach(b => {
        const option = document.createElement("option");
        option.value = b.idSucursal;
        option.textContent = b.nombreSucursal;
        branchSelect.appendChild(option);

        const optionEdit = document.createElement("option");
        optionEdit.value = b.idSucursal;
        optionEdit.textContent = b.nombreSucursal;
        document.querySelector("#branch-select-edit").appendChild(optionEdit);
        
        const optionAdd = document.createElement("option");
        optionAdd.value = b.idSucursal;
        optionAdd.textContent = b.nombreSucursal;
        document.querySelector("#branch-select-add").appendChild(optionAdd);
    });

    branchSelect.addEventListener("change", async () => {
        checkoutSelect.innerHTML = '<option selected disabled value="">Seleccione una caja</option>';
        checkoutSelect.disabled = branchSelect.value === "";
        
        if(branchSelect.value !== "") {
            let checkouts = await fetch(`/api/cajas/sucursal/${branchSelect.value}`).then(r => r.json());
            

            checkouts.forEach(c => {
                const option = document.createElement("option");
                option.value = c.idCaja;
                option.textContent = `Caja ${c.numCaja}`;
                option.dataset.num = c.numCaja;
                checkoutSelect.appendChild(option);
            });
        }
        
        setupReadOnlyMode();
    });

    document.querySelector("#logout-btn").addEventListener("click", () => {
        window.localStorage.removeItem("userData");
        window.location.href = "/login";
    })
    
    checkoutSelect.addEventListener("change", setupReadOnlyMode);
    editBtn.addEventListener("click", setupEditMode);
    cancelBtn.addEventListener("click", setupReadOnlyMode);

    document.querySelector("#checkout-number-edit").addEventListener('input', (e) => {
        const input = e.target;
        if (input.value !== '' && input.value < 1) {
          input.value = 1;
        }

        checkChanges();
    });

    document.querySelector("#branch-select-edit").addEventListener("change", checkChanges)
    document.querySelector("#add-checkout-modal").addEventListener("hidden.bs.modal", clearAddModal);
    document.querySelector("#reset-btn").addEventListener("click", clearAddModal);

    document.querySelector("#checkout-number-add").addEventListener('input', (e) => {
        const input = e.target;
        if (input.value !== '' && input.value < 1) {
          input.value = 1;
        }
    });


    delBtn.addEventListener("click", async (event) => {
        try {
            const response = await fetch(`/api/cajas/verificar/eliminar/${checkoutSelect.value}`);

            const data = await response.json();

            if(!response.ok) {
                throw new Error(data.msg);
            }

            Swal.fire({
                title: 'Confirmación de Eliminación',
                text: `¿Desea eliminar la caja: ${checkoutSelect.querySelector(`option[value='${checkoutSelect.value}']`).textContent}?`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#dc3545',
                cancelButtonColor: '#51585e',
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar'
            }).then(async (result) => {
                if (result.isConfirmed) {
                    await fetch(`/api/cajas/eliminar/${checkoutSelect.value}`, {
                        method:"DELETE"
                    });

                    Swal.fire({
                        icon: 'success',
                        title: '¡Éxito!',
                        text: 'Caja eliminada exitosamente.',
                        confirmButtonColor: '#14A44d',
                        confirmButtonText: 'Aceptar'
                    }).then(() => {
                        window.location.reload();
                    });
                }
            });
        } catch (error) {
            if (error.message.includes('caja en uso')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Caja en uso',
                    text: 'La caja tiene registros asociados y no puede ser eliminada.',
                    confirmButtonText: 'Entendido'
                });
            } else if (error.message.includes('caja inexistente')){
                Swal.fire({
                    icon: 'error',
                    title: 'Caja inexistente',
                    text: 'No existe una caja con ese identificador',
                    confirmButtonText: 'Entendido'
                });
            }
        }
    });

    document.querySelectorAll(".del").forEach(el => {
        if(!auth.del) el.remove();
    });

    document.querySelectorAll(".modify, .del").forEach(el => {
        if(!auth.mod) el.remove();
    });
    
    document.querySelectorAll(".create").forEach(el => {
        if(!auth.create) el.remove();
    });

    document.querySelector("#checkout-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        
        const submitBtn = document.querySelector("#submit-btn-modify");
        submitBtn.disabled = true;
        
        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";
        
        try {
            const formData = {
                idCaja: checkoutSelect.value,
                numCaja: document.querySelector("#checkout-number-edit").value,
                sucursal: {
                    idSucursal: branchSelect.value
                }
            };

            const response = await fetch("/api/cajas/verificar/edicion", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            });

            const data = await response.json();
            
            if (!response.ok) {
                throw new Error(data.msg || 'Error en la solicitud');
            }

            await fetch("/api/cajas/modificar", {
                method:"POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            });
            
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Caja modificada correctamente',
                confirmButtonColor: '#14A44d',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                window.location.reload();
            });
            
        } catch (error) {
            if (error.message.includes('caja existente')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Caja existente',
                    text: 'El número de caja ya está en uso en esta sucursal.',
                    confirmButtonText: 'Entendido'
                });
                document.getElementById('checkout-number-edit').focus();
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

    document.querySelector("#checkout-form-add").addEventListener("submit", async function(event) {
        event.preventDefault();
        const submitBtn = document.querySelector("#submit-btn-add");
        submitBtn.disabled = true;
        
        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";

        try {
            const formData = {
                numCaja: document.querySelector("#checkout-number-add").value,
                sucursal: {
                    idSucursal: document.querySelector("#branch-select-add").value
                }
            };

            const response = await fetch("/api/cajas/verificar/crear", {
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

            await fetch("/api/cajas/agregar", {
                method:"POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            });
            
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Caja registrada correctamente',
                confirmButtonColor: '#14A44d',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                window.location.reload();
            });
            
        } catch (error) {
            if (error.message.includes('caja existente')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Caja existente',
                    text: 'El número de caja ya está en uso en esta sucursal.',
                    confirmButtonText: 'Entendido'
                });
                document.getElementById('checkout-number-add').focus();
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
            submitBtn.innerHTML = '<i class="fas fa-save me-1"></i> Guardar Caja';
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
    });
});

async function loadCheckoutData() {
    const editBtn = document.querySelector("#edit-btn");
    const delBtn = document.querySelector("#del-btn");
    const checkoutNumber = document.querySelector("#checkout-number-edit"); 
    const branchName = document.querySelector("#branch-name-edit");
    const branchSelectEdit = document.querySelector("#branch-select-edit");
    
    document.querySelector("#submit-btn-modify").disabled = true;
    
    if(checkoutSelect.value !== "") {
        editBtn?.classList.remove("d-none");
        delBtn?.classList.remove("d-none");
        
        let id =parseInt(checkoutSelect.value);
        let id_copy = id;
        for(let k = 2; true; k++) {
            if(id < k)
                break;

            id -= k;
        }


        let selectedCheckoutData = {
                idCaja: checkoutSelect.value,
                numCaja: checkoutSelect.selectedOptions.item(0).dataset.num,
            }
        
        // Basic info
        checkoutNumber.value = selectedCheckoutData.numCaja;
        checkoutNumber.dataset.value = selectedCheckoutData.numCaja;

        branchName.value = branchSelect.selectedOptions.item(0).textContent;
        branchSelectEdit.value = branchSelect.value;
        branchSelectEdit.dataset.value = branchSelect.value;
                
        return;
    }

    editBtn?.classList.add("d-none");
    delBtn?.classList.add("d-none");

    // Clear all fields
    checkoutNumber.value = "";
    checkoutNumber.dataset.value = "";
    branchName.value = "";

    branchSelectEdit.value = "";
    branchSelectEdit.dataset.value = "";
            
}

function checkChanges() {
    const inputs = document.querySelectorAll("#checkout-number-edit, #branch-select-edit");

    for(const i of inputs) {
        if(i.dataset.value !== i.value) {
            document.querySelector("#submit-btn-modify").disabled = false;
            return;
        }
    }

    document.querySelector("#submit-btn-modify").disabled = true;
}

function setupEditMode() {
    document.querySelector("#edit-btn")?.classList.add("d-none");
    document.querySelector("#del-btn")?.classList.add("d-none");

    // Enable input
    document.querySelector("#checkout-number-edit").disabled = false;
    document.querySelector("#branch-name-edit").classList.add("d-none");
    document.querySelector("#branch-select-edit").classList.remove("d-none");

    // Show buttons
    const form = document.querySelector("#checkout-form");
    const btnDiv = form.querySelector("fieldset+div");
    btnDiv.classList.remove("d-none");
    btnDiv.classList.add("d-md-flex");
}

function setupReadOnlyMode() {
    loadCheckoutData();

    // Disable input
    document.querySelector("#checkout-number-edit").disabled = true;
    document.querySelector("#branch-name-edit").classList.remove("d-none");
    document.querySelector("#branch-select-edit").classList.add("d-none");
   
    // Hide buttons
    const form = document.querySelector("#checkout-form");
    const btnDiv = form.querySelector("fieldset+div");
    btnDiv.classList.add("d-none");
    btnDiv.classList.remove("d-md-flex");
}

function clearAddModal() {
    const inputs = document.querySelectorAll("#checkout-number-add, #branch-select-add");
    inputs.forEach(input => input.value = "");
}