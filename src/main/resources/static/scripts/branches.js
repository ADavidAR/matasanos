const branchSelect = document.querySelector("#select-branch");

window.addEventListener("DOMContentLoaded", async () => {

    const roleH5 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    const editBtn = document.querySelector("#edit-btn");
    const cancelBtn = document.querySelector("#cancel-btn-modify");
    const delBtn = document.querySelector("#del-btn");
    
    const selectCityEdit = document.querySelector("#city-select-edit");
    const selectNeighborhoodEdit = document.querySelector("#neighborhood-select-edit");
    const selectCityAdd = document.querySelector("#city-select-add");
    const selectNeighborhoodAdd = document.querySelector("#neighborhood-select-add");
    roleH5.textContent = userData.rol.nombreRol;

    let branches = await fetch("/api/sucursales").then(r => r.json());

    let cities = await fetch("/api/ciudades").then( r => r.json());

    branches.forEach(b => {
        const option = document.createElement("option");
        option.value = b.idSucursal;
        option.textContent = b.nombreSucursal;
        branchSelect.appendChild(option);
    });

    cities.forEach(c => {
            const optionAdd = document.createElement("option");
            optionAdd.value = c.idCiudad;
            optionAdd.textContent = c.ciudad;
            document.querySelector("#city-select-add").appendChild(optionAdd);
            
            const optionEdit = document.createElement("option");
            optionEdit.value = c.idCiudad;
            optionEdit.textContent = c.ciudad;
            document.querySelector("#city-select-edit").appendChild(optionEdit);
    });

    branchSelect.addEventListener("change", setupReadOnlyMode);
    editBtn.addEventListener("click", setupEditMode);
    cancelBtn.addEventListener("click", setupReadOnlyMode);



    document.querySelector("#branch-name-edit").addEventListener("input", checkChanges);
    selectCityEdit.addEventListener("change", updateNeighborhoddOptions.bind(null, selectCityEdit,selectNeighborhoodEdit));
    selectNeighborhoodEdit.addEventListener("change", checkChanges);
    selectCityAdd.addEventListener("change", updateNeighborhoddOptions.bind(null, selectCityAdd, selectNeighborhoodAdd));
    document.querySelector("#ref-edit").addEventListener("input", checkChanges);

    document.querySelector("#add-branch-modal").addEventListener("hidden.bs.modal", clearAddModal);
    document.querySelector("#reset-btn").addEventListener("click", clearAddModal);

    delBtn.addEventListener("click", async (event) => {
        try {
            const response = await fetch(`/api/sucursales/verificar/eliminar/${branchSelect.value}`);

            const data = await response.json();

            if(!response.ok) {
                throw new Error(data.msg);
            }

            Swal.fire({
                title: 'Confirmación de Eliminación',
                text: `¿Desea eliminar la sucursal: ${branchSelect.querySelector(`option[value='${branchSelect.value}']`).textContent}?`,
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#dc3545',
                cancelButtonColor: '#51585e',
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar'
            }).then(async (result) => {
                if (result.isConfirmed) {
                    await fetch(`/api/sucursales/eliminar/${branchSelect.value}`, {
                        method:"DELETE"
                    });

                    Swal.fire({
                        icon: 'success',
                        title: '¡Éxito!',
                        text: 'Sucursal eliminada exitosamente.',
                        confirmButtonColor: '#14A44d',
                        confirmButtonText: 'Aceptar'
                    }).then(() => {
                        window.location.reload();
                    });
                }
            });
        } catch (error) {
            if (error.message.includes('sucursal en uso')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Sucursal en uso',
                    text: 'La sucursal tiene registros asociados y no puede ser eliminada.',
                    confirmButtonText: 'Entendido'
                });
            } else if (error.message.includes('sucursal inexistente')){
                Swal.fire({
                    icon: 'error',
                    title: 'Sucursal inexistente',
                    text: 'No existe una sucursal con ese identificador',
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

    document.querySelector("#branch-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        
        const submitBtn = document.querySelector("#submit-btn-modify");
        submitBtn.disabled = true;
        
        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";
        
        try {
            const formData = {
                idSucursal: 0,
                nombreSucursal: document.querySelector("#branch-name-edit").value,
                direccion: {
                    idDireccion: document.querySelector("#address-div").dataset.id,
                    colonia: {
                        idColonia: document.querySelector("#neighborhood-select-edit").value,
                        ciudad: {
                            idCiudad: document.querySelector("#city-select-edit").value,
                        }
                    },
                    referencia: document.querySelector(" #ref-edit").value
                }
            };

            const response = await fetch("/api/sucursales/verificar/edicion", {
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

            await fetch("/api/sucursales/modificar", {
                method:"POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            });
            
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Sucursal modificada correctamente',
                confirmButtonColor: '#14A44d',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                window.location.reload();
            });
            
        } catch (error) {
            if (error.message.includes('sucursal existente')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Sucursal existente',
                    text: 'El nombre o código de sucursal ya está en uso.',
                    confirmButtonText: 'Entendido'
                });
                document.getElementById('branch-name').focus();
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

    document.querySelector("#branch-form-add").addEventListener("submit", async function(event) {
        event.preventDefault();
        const submitBtn = document.querySelector("#submit-btn-add");
        submitBtn.disabled = true;
        
        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";

        try {
            const formData = {
                idSucursal: 0,
                nombreSucursal: document.querySelector("#branch-name-add").value,
                direccion: {
                    colonia: {
                        idColonia: document.querySelector("#neighborhood-select-add").value,
                        ciudad: {
                            idCiudad: document.querySelector("#city-select-add").value,
                        }
                    },
                    referencia: document.querySelector("#ref-add").value
                }
            };

            const response = await fetch("/api/sucursales/verificar/crear", {
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

            await fetch("/api/sucursales/agregar", {
                method:"POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(formData)
            });
            
            Swal.fire({
                icon: 'success',
                title: 'Éxito',
                text: 'Sucursal registrada correctamente',
                confirmButtonColor: '#14A44d',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                window.location.reload();
            });
            
        } catch (error) {
            if (error.message.includes('sucursal existente')) {
                Swal.fire({
                    icon: 'error',
                    title: 'Sucursal existente',
                    text: 'El nombre o código de sucursal ya está en uso.',
                    confirmButtonText: 'Entendido'
                });
                document.getElementById('branch-name-add').focus();
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
            submitBtn.innerHTML = '<i class="fas fa-save me-1"></i> Guardar Sucursal';
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

async function loadBranchData() {
    const editBtn = document.querySelector("#edit-btn");
    const delBtn = document.querySelector("#del-btn");
    const branchName  = document.querySelector("#branch-name-edit"); 
    const neighborhood  = document.querySelector("#neighborhood-edit");
    const neighborhoodSelect  = document.querySelector("#neighborhood-select-edit");
    const city  = document.querySelector("#city-edit");
    const citySelect  = document.querySelector("#city-select-edit");
    const ref  = document.querySelector("#ref-edit");
    
    document.querySelector("#submit-btn-modify").disabled = true;
    
    if(branchSelect.value !== "") {
        editBtn?.classList.remove("d-none");
        delBtn?.classList.remove("d-none");
        
        let selectedBranchData = await fetch(`/api/sucursales/${branchSelect.value}`).then(r => r.json());

        // Basic info
        
        //branch name
        branchName.value = selectedBranchData.nombreSucursal;
        branchName.dataset.value = selectedBranchData.nombreSucursal;

        //city
        city.value = selectedBranchData.direccion.colonia.ciudad.ciudad;
        
        citySelect.value = selectedBranchData.direccion.colonia.ciudad.idCiudad;
        citySelect.dataset.value = selectedBranchData.direccion.colonia.ciudad.idCiudad;

        //adress 

        document.querySelector("#address-div").dataset.id = selectedBranchData.direccion.idDireccion;
        //neighborhood
        neighborhood.value = selectedBranchData.direccion.colonia.nombreColonia;

        neighborhoodSelect.value = selectedBranchData.direccion.colonia.idColonia;
        neighborhoodSelect.dataset.value = selectedBranchData.direccion.colonia.idColonia;
        
        //reference
        ref.value = selectedBranchData.direccion.referencia;
        ref.dataset.value = selectedBranchData.direccion.referencia;
                
        return;
    }

    editBtn?.classList.add("d-none");
    delBtn?.classList.add("d-none");

    // Clear all fields

    // Branch name
    branchName.value = "";
    branchName.dataset.value = ""

    //city
    city.value = "";

    citySelect.value = "";
    citySelect.dataset.value = "";

    //neighborhood
    neighborhood.value = "";

    neighborhoodSelect.value = "";
    neighborhoodSelect.dataset.value = "";
    
    //reference
    ref.value = "";
}

function checkChanges() {
    const inputs = document.querySelectorAll("#branch-name-edit, #neighborhood-select-edit, #city-select-edit, #ref-edit");

    for(const i of inputs) {
        if(i.dataset.value !== i.value) {
            document.querySelector("#submit-btn-modify").disabled = false;
            return;
        }
    }

    document.querySelector("#submit-btn-modify").disabled = true;
}

async function setupEditMode() {

    document.querySelector("#edit-btn")?.classList.add("d-none");
    document.querySelector("#del-btn")?.classList.add("d-none");

    const city  = document.querySelector("#city-edit");

    // Enable all inputs
    document.querySelectorAll( "#branch-name-edit, #ref-edit").forEach(input => {
        input.disabled = false;
    });
    
    document.querySelectorAll( "#neighborhood-select-edit, #city-select-edit").forEach(input => {
        input.classList.remove("d-none");
    });

    document.querySelectorAll( "#neighborhood-edit, #city-edit").forEach(input => {
        input.classList.add("d-none");
    });

    //load neighborhoods from current city

    let neighborFromCity = await fetch(`/api/colonias/${document.querySelector("#city-select-edit").value}`).then(r => r.json());

    neighborFromCity.forEach((n) => {
        const option = document.createElement("option");

        option.value = n.idColonia;
        option.textContent =n.nombreColonia

        document.querySelector("#neighborhood-select-edit").appendChild(option);
    });

    // Show buttons
    const form = document.querySelector("#branch-form");
    const btnDiv = form.querySelector("fieldset+div");
    btnDiv.classList.remove("d-none");
    btnDiv.classList.add("d-md-flex");
}

function setupReadOnlyMode() {
    loadBranchData();

    // Disable all inputs
    document.querySelectorAll( "#branch-name-edit, #ref-edit").forEach(input => {
        input.disabled = true;
    });
    
    document.querySelectorAll( "#neighborhood-select-edit, #city-select-edit").forEach(input => {
        input.classList.add("d-none");
    });

    document.querySelectorAll( "#neighborhood-edit, #city-edit").forEach(input => {
        input.classList.remove("d-none");
    });

   
    // Hide buttons
    const form = document.querySelector("#branch-form");
    const btnDiv = form.querySelector("fieldset+div");
    btnDiv.classList.add("d-none");
    btnDiv.classList.remove("d-md-flex");
}

function clearAddModal() {
    const inputs = document.querySelectorAll("#branch-name-add, #neighborhood-select-add, #city-select-add, #ref-add");
    inputs.forEach(input => input.value = "");
}

async function updateNeighborhoddOptions(citySelect, neighborhoodSelect) {
    checkChanges();
    
    if(citySelect.value === "") {
        neighborhoodSelect.disabled = true;
        return
    }

    let k = parseInt(citySelect.value);

    let neighborFromCity = await fetch(`/api/colonias/${citySelect.value}`).then(r => r.json());

    
    neighborhoodSelect.querySelectorAll("option[value='']~option").forEach(el => {el.remove();});
    neighborhoodSelect.disabled = false;
    neighborhoodSelect.value = "";

    neighborFromCity.forEach((n) => {
        const option = document.createElement("option");

        option.value = n.idColonia;
        option.textContent =n.nombreColonia;
        neighborhoodSelect.appendChild(option);
    });

}