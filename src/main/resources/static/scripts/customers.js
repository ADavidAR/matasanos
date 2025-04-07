document.addEventListener("DOMContentLoaded", async () => {
    const roleH5 = document.querySelector("#role");
    const optionsNav = document.querySelector("#nav-options");
    const customerModal = new bootstrap.Modal(document.querySelector("#customer-modal"));
    const customerForm = document.querySelector("#customer-form");
    const customerModalLabel = document.querySelector("#customer-modal-label");
    
    //form elements
    const firstNameInput = document.querySelector("#first-name");
    const secondNameInput = document.querySelector("#second-name");
    const firstLastNameInput = document.querySelector("#first-lastname");
    const secondLastNameInput = document.querySelector("#second-lastname");
    const dniInput = document.querySelector("#dni");
    const rtnInput = document.querySelector("#rtn");
    const citySelect = document.querySelector("#city-select");
    const neighborhoodSelect = document.querySelector("#neighborhood-select");
    const addressRefInput = document.querySelector("#address-ref");
    const customerIdInput = document.querySelector("#customer-id");
    const personIdInput = document.querySelector("#person-id");
    const addressIdInput = document.querySelector("#address-id");
    
    roleH5.textContent = userData.rol.nombreRol;


    const cities = await fetch("/api/ciudades").then(r => r.json());
    cities.forEach(city => {
        const option = document.createElement("option");
        option.value = city.idCiudad;
        option.textContent = city.ciudad;
        citySelect.appendChild(option);
    });

    await loadCustomers();

    
    document.querySelector("#search-btn").addEventListener("click", searchCustomers);
    document.querySelector("#search-customer").addEventListener("keypress", (e) => {
        if (e.key === "Enter") searchCustomers();
    });

    neighborhoodSelect.addEventListener("change", checkChanges);
    firstNameInput.addEventListener("input", checkChanges);
    secondNameInput.addEventListener("input", checkChanges);
    firstLastNameInput.addEventListener("input", checkChanges);
    secondLastNameInput.addEventListener("input", checkChanges);
    dniInput.addEventListener("input", checkChanges);
    dniInput.addEventListener('input', function (e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length <= 4) {
            e.target.value = value;
        } else if (value.length <= 8) {
            e.target.value = value.slice(0, 4) + '-' + value.slice(4);
        } else if (value.length <= 12) {
            e.target.value = value.slice(0, 4) + '-' + value.slice(4, 8) + '-' + value.slice(8);
        } else {
            e.target.value = value.slice(0, 4) + '-' + value.slice(4, 8) + '-' + value.slice(8, 13);
        }
    });
    rtnInput.addEventListener("input", checkChanges);
    addressRefInput.addEventListener("input", checkChanges);

    citySelect.addEventListener("change", async () => {
        if (citySelect.value === "") {
            neighborhoodSelect.disabled = true;
            return;
        }
        checkChanges()
        const neighborhoods = await fetch(`/api/colonias/${citySelect.value}`).then(r => r.json());
        
        neighborhoodSelect.innerHTML = '<option selected disabled value="">Seleccione una colonia</option>';
        neighborhoods.forEach(neighborhood => {
            const option = document.createElement("option");
            option.value = neighborhood.idColonia;
            option.textContent = neighborhood.nombreColonia;
            neighborhoodSelect.appendChild(option);
        });
        
        neighborhoodSelect.disabled = false;
    });

    // add customer button
    document.querySelector(".create").addEventListener("click", () => {
        customerModalLabel.textContent = "Nuevo Cliente";
        customerModalLabel.dataset.add = true;
        customerForm.reset();
        customerIdInput.value = "";
        personIdInput.value = "";
        neighborhoodSelect.innerHTML = '<option selected disabled value="">Seleccione una colonia</option>';
        neighborhoodSelect.disabled = true;
        document.querySelector("#submit-btn").disabled = false;
    });

    //reset button
    document.querySelector("#reset-btn").addEventListener("click", () => {
        customerForm.reset();
        neighborhoodSelect.innerHTML = '<option selected disabled value="">Seleccione una colonia</option>';
        neighborhoodSelect.disabled = true;
    });

    //submit form (edit mode or add mode)
    customerForm.addEventListener("submit", async (e) => {
        e.preventDefault();
        
        const submitBtn = document.querySelector("#submit-btn");
        submitBtn.disabled = true;
        submitBtn.innerHTML = "<span class='spinner-border spinner-border-sm' role='status' aria-hidden='true'></span> Procesando...";
        
        try {
            const customerData = {
                idCliente: customerIdInput.value || 0,
                rtn: rtnInput.value || null,
                persona: {
                    idPersona: personIdInput.value || 0,
                    primerNombre: firstNameInput.value,
                    segundoNombre: secondNameInput.value || null,
                    primerApellido: firstLastNameInput.value,
                    segundoApellido: secondLastNameInput.valu || null,
                    dni: dniInput.value,
                    direccion: {
                        idDireccion: addressIdInput.value,
                        referencia: addressRefInput.value,
                        colonia: {
                            idColonia: neighborhoodSelect.value
                        }
                    }
                }
            };

            //modify / add
            const endpointValidate = customerIdInput.value ? "/api/clientes/verificar/edicion" : "/api/clientes/verificar/crear";
            const endpointFinal = customerIdInput.value ? "/api/clientes/modificar" : "/api/clientes/agregar";
            const method = customerIdInput.value ? "PUT" : "POST";
            const response = await fetch(endpointValidate, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(customerData)
            });

            console.log(customerData)

            const data = await response.json();
            
            if (!response.ok) {
                throw new Error(data.msg || "Error en la solicitud");
            }

            if(data.msg !== undefined) {
                let fullName = `${customerData.persona.primerNombre} ${customerData.persona.segundoNombre} ${customerData.persona.primerApellido} ${customerData.persona.segundoApellido}`;
                
                if(data.fullName.toLowerCase() !== fullName.toLowerCase()) {
                    console.log("conflicto")
                    Swal.fire({
                        title: "Nombres distintos",
                        text: `El DNI pertenece a una persona registrada con otro nombre. ¿Desea combiar los datos de esa persona?`,
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#dc3545",
                        cancelButtonColor: "#14A44d",
                        confirmButtonText: "Cambiar",
                        cancelButtonText: "No Cambiar"
                    }).then(async (result) => {
                        customerData.persona.idPersona = data.id;
                        if (result.isConfirmed) {
                            await fetch("/api/clientes/agregar/mod", {
                                method:"POST",
                                headers: { "Content-Type": "application/json"},
                                body: JSON.stringify(customerData)
                            })
                            
                        } else if (result.dismiss === Swal.DismissReason.cancel) {
                            await fetch("/api/clientes/agregar/existente", {
                                method:"POST",
                                headers: { "Content-Type": "application/json"},
                                body: JSON.stringify(customerData)
                            })
                        }

                        customerModal.hide();
                        loadCustomers();
                    })
                    return;
                }
            }

            await fetch(endpointFinal, {
                method,
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(customerData)
            })

            Swal.fire({
                icon: "success",
                title: "Éxito",
                text: customerIdInput.value ? "Cliente actualizado correctamente" : "Cliente registrado correctamente",
                confirmButtonColor: "#14A44d",
                confirmButtonText: "Aceptar"
            }).then(() => {
                customerModal.hide();
                loadCustomers();
            });
            
        } catch (error) {
            let errorMessage = "Ocurrió un error al procesar la solicitud";
            
            if (error.message.includes("dni existente")) {
                errorMessage = "El DNI ya está registrado para otro cliente";
                dniInput.focus();
            } else if (error.message.includes("rtn existente")) {
                errorMessage = "El RTN ya está registrado para otro cliente";
                rtnInput.focus();
            } else {
                errorMessage = error.message || errorMessage;
            }

            Swal.fire({
                icon: "error",
                title: "Error",
                text: errorMessage,
                confirmButtonText: "Entendido"
            });
        } finally {
            submitBtn.disabled = false;
            submitBtn.innerHTML = '<i class="fas fa-save me-1"></i> Guardar Cliente';
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

    document.querySelectorAll(".del").forEach(el => {
        if(!auth.del) el.remove();
    });

    document.querySelectorAll(".modify, .del").forEach(el => {
        if(!auth.mod) el.remove();
    });
    
    document.querySelectorAll(".create").forEach(el => {
        if(!auth.create) el.remove();
    });

    document.querySelector("#logout-btn").addEventListener("click", () => {
        window.localStorage.removeItem("userData");
        window.location.href = "/login";
    })
});

async function loadCustomers() {
    const response = await fetch("/api/clientes");
    const customers = await response.json();
    const tableBody = document.querySelector("#customers-table-body");
    
    tableBody.innerHTML = "";
    
    customers.forEach(customer => {
        const row = document.createElement("tr");
        
        row.innerHTML = `
            <td>${customer.persona.primerNombre} ${customer.persona.segundoNombre || ""} ${customer.persona.primerApellido} ${customer.persona.segundoApellido || ""}</td>
            <td>${customer.persona.dni || "N/A"}</td>
            <td>${customer.rtn || "N/A"}</td>
            <td>${new Date(customer.fechaCreacion).toLocaleDateString()}</td>
        `;

        const tdBtnContainer = document.createElement("td");
        const viewBtn = document.createElement("button");
        viewBtn.setAttribute("class","btn btn-sm btn-primary view-btn")
        viewBtn.innerHTML = '<i class="fas fa-eye"></i>'
        viewBtn.dataset.id = customer.idCliente;
        viewBtn.addEventListener("click", viewCustomer.bind(null, customer.idCliente));
        tdBtnContainer.appendChild(viewBtn);

        if(auth.mod ) {
            tdBtnContainer.appendChild(document.createTextNode(" "));
            const btnEdit = document.createElement("button");
            btnEdit.setAttribute("class","btn btn-sm btn-warning edit-btn");
            btnEdit.innerHTML = '<i class="fas fa-edit"></i>'
            btnEdit.addEventListener("click", editCustomer.bind(null, customer.idCliente));
            tdBtnContainer.appendChild(btnEdit);
        }

        if(auth.mod ) {
            tdBtnContainer.appendChild(document.createTextNode(" "));
            const btnDel = document.createElement("button");
            btnDel.setAttribute("class","btn btn-sm btn-danger delete-btn");
            btnDel.innerHTML = '<i class="fas fa-trash"></i>'
            btnDel.addEventListener("click", deleteCustomer.bind(null, customer.idCliente));
            tdBtnContainer.appendChild(btnDel);
        }

        row.appendChild(tdBtnContainer)
        
        tableBody.appendChild(row);
    });
}

async function viewCustomer(id) {
    
    
    const customer = await fetch(`/api/clientes/${id}`).then(r => r.json());
    
    Swal.fire({
        title: `${customer.persona.primerNombre} ${customer.persona.primerApellido}`,
        html: `
            <div class="text-start">
                <p><strong>Nombre completo:</strong> ${customer.persona.primerNombre} ${customer.persona.segundoNombre || ""} ${customer.persona.primerApellido} ${customer.persona.segundoApellido || ""}</p>
                <p><strong>DNI:</strong> ${customer.persona.dni || "N/A"}</p>
                <p><strong>RTN:</strong> ${customer.rtn || "N/A"}</p>
                <p><strong>Fecha registro:</strong> ${new Date(customer.fechaCreacion).toLocaleDateString()}</p>
                <p><strong>Dirección:</strong> ${customer.persona.direccion.colonia.nombreColonia}, ${customer.persona.direccion.colonia.ciudad.ciudad}</p>
                <p><strong>Referencia:</strong> ${customer.persona.direccion.referencia}</p>
            </div>
        `,
        confirmButtonText: "Cerrar"
    });
}

async function editCustomer(id) {
    const customerModal = new bootstrap.Modal(document.querySelector("#customer-modal"));
    const customerModalLabel = document.querySelector("#customer-modal-label");
    customerModalLabel.dataset.add = false;
    const customer = await fetch(`/api/clientes/${id}`).then(r => r.json());
    
    customerModalLabel.textContent = "Editar Cliente";

    //setup customer data
    const customerIdInput = document.querySelector("#customer-id");
    const personIdInput = document.querySelector("#person-id");
    const addressIdInput = document.querySelector("#address-id");
    const firstNameInput = document.querySelector("#first-name");
    const secondNameInput = document.querySelector("#second-name");
    const firstLastnameInput = document.querySelector("#first-lastname");
    const secondLastnameInput = document.querySelector("#second-lastname");
    const dniInput = document.querySelector("#dni");
    const rtnInput = document.querySelector("#rtn");
    const citySelect = document.querySelector("#city-select");
    const neighborhoodSelect = document.querySelector("#neighborhood-select");
    const ref = document.querySelector("#address-ref");

    console.log(customer)
    customerIdInput.value = customer.idCliente;
    personIdInput.value = customer.persona.idPersona;
    addressIdInput.value = customer.persona.direccion.idDireccion;
    
    firstNameInput.value = customer.persona.primerNombre;
    firstNameInput.dataset.value = customer.persona.primerNombre;

    secondNameInput.value = customer.persona.segundoNombre || "";
    secondNameInput.dataset.value = customer.persona.segundoNombre || "";
    firstLastnameInput.value = customer.persona.primerApellido;
    firstLastnameInput.dataset.value = customer.persona.primerApellido;
    secondLastnameInput.value = customer.persona.segundoApellido || "";
    secondLastnameInput.dataset.value = customer.persona.segundoApellido || "";
    dniInput.value = customer.persona.dni;
    dniInput.dataset.value = customer.persona.dni;
    rtnInput.value = customer.rtn || "";
    rtnInput.dataset.value = customer.rtn || "";

    
    citySelect.value = customer.persona.direccion.colonia.ciudad.idCiudad;
    citySelect.dataset.value = customer.persona.direccion.colonia.ciudad.idCiudad; 

    const neighborhoods = await fetch(`/api/colonias/${citySelect.value}`).then(r => r.json());
        
    neighborhoodSelect.innerHTML = '<option selected disabled value="">Seleccione una colonia</option>';
    neighborhoods.forEach(neighborhood => {
        const option = document.createElement("option");
        option.value = neighborhood.idColonia;
        option.textContent = neighborhood.nombreColonia;
        neighborhoodSelect.appendChild(option);
    });
    neighborhoodSelect.disabled = false;

    neighborhoodSelect.value = customer.persona.direccion.colonia.idColonia;
    neighborhoodSelect.dataset.value = customer.persona.direccion.colonia.idColonia;
    
    ref.value = customer.persona.direccion.referencia;
    
    customerModal.show();
}

async function deleteCustomer(id) {
    try {
        const response = await fetch(`/api/clientes/verificar/eliminar/${id}`);
        const data = await response.json();

        if(!response.ok) {
            throw new Error(data.msg);
        }

        const customer = await fetch(`/api/clientes/${id}`).then(r => r.json());
        const fullName = `${customer.persona.primerNombre} ${customer.persona.primerApellido}`;

        Swal.fire({
            title: "Confirmación de Eliminación",
            text: `¿Desea eliminar al cliente: ${fullName}?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#dc3545",
            cancelButtonColor: "#51585e",
            confirmButtonText: "Eliminar",
            cancelButtonText: "Cancelar"
        }).then(async (result) => {
            if (result.isConfirmed) {
                await fetch(`/api/clientes/eliminar/${id}`, {
                    method: "DELETE"
                });

                Swal.fire({
                    icon: "success",
                    title: "¡Éxito!",
                    text: "Cliente eliminado exitosamente.",
                    confirmButtonColor: "#14A44d",
                    confirmButtonText: "Aceptar"
                }).then(() => {
                    loadCustomers();
                });
            }
        });
    } catch (error) {
        if (error.message.includes("cliente en uso")) {
            Swal.fire({
                icon: "error",
                title: "Cliente en uso",
                text: "El cliente tiene registros asociados y no puede ser eliminado.",
                confirmButtonText: "Entendido"
            });
        } else if (error.message.includes("cliente inexistente")){
            Swal.fire({
                icon: "error",
                title: "Cliente inexistente",
                text: "No existe un cliente con ese identificador",
                confirmButtonText: "Entendido"
            });
        }
    }
}

async function searchCustomers() {
    const searchTerm = document.querySelector("#search-customer").value.trim().toLowerCase();
    
    if (!searchTerm) {
        await loadCustomers();
        return;
    }

    const response = await fetch("/api/clientes");
    const allCustomers = await response.json();
    const filteredCustomers = allCustomers.filter(customer => 
        `${customer.persona.primerNombre} ${`${customer.persona.segundoNombre} ` || ""}${customer.persona.primerApellido}${` ${customer.persona.segundoApellido}` || ""}`.toLowerCase().includes(searchTerm) ||
        customer.persona.dni.toLowerCase().includes(searchTerm) ||
        (customer.rtn && customer.rtn.toLowerCase().includes(searchTerm))
    );

    const tableBody = document.querySelector("#customers-table-body");
    tableBody.innerHTML = "";
    
    filteredCustomers.forEach(customer => {
        const row = document.createElement("tr");
        
        row.innerHTML = `
            <td>${customer.persona.primerNombre} ${customer.persona.segundoNombre || ""} ${customer.persona.primerApellido} ${customer.persona.segundoApellido || ""}</td>
            <td>${customer.persona.dni}</td>
            <td>${customer.rtn || "N/A"}</td>
            <td>${new Date(customer.fechaCreacion).toLocaleDateString()}</td>
        `;

        const tdBtnContainer = document.createElement("td");
        const viewBtn = document.createElement("button");
        viewBtn.setAttribute("class","btn btn-sm btn-primary view-btn")
        viewBtn.innerHTML = '<i class="fas fa-eye"></i>'
        viewBtn.dataset.id = customer.idCliente;
        viewBtn.addEventListener("click", viewCustomer.bind(null, customer.idCliente));
        tdBtnContainer.appendChild(viewBtn);

        if(auth.mod ) {
            tdBtnContainer.appendChild(document.createTextNode(" "));
            const btnEdit = document.createElement("button");
            btnEdit.setAttribute("class","btn btn-sm btn-warning edit-btn");
            btnEdit.innerHTML = '<i class="fas fa-edit"></i>'
            btnEdit.addEventListener("click", editCustomer.bind(null, customer.idCliente));
            tdBtnContainer.appendChild(btnEdit);
        }

        if(auth.mod ) {
            tdBtnContainer.appendChild(document.createTextNode(" "));
            const btnDel = document.createElement("button");
            btnDel.setAttribute("class","btn btn-sm btn-danger delete-btn");
            btnDel.innerHTML = '<i class="fas fa-trash"></i>'
            btnDel.addEventListener("click", deleteCustomer.bind(null, customer.idCliente));
            tdBtnContainer.appendChild(btnDel);
        }

        row.appendChild(tdBtnContainer)
        tableBody.appendChild(row);
    });
}

function checkChanges() {
    const submitBtn = document.querySelector("#submit-btn");

    if(document.querySelector("#customer-modal-label").dataset.add === "true") {
        submitBtn.disabled = false;
        return;
    }

    const inputs = document.querySelectorAll("#first-name, #second-name, #first-lastname, #second-lastname, #dni, #rtn, #neighborhood-select, #city-select, #address-ref");
    
    for(const input of inputs) {
        if(input.value !== input.dataset.value) {
            submitBtn.disabled = false;
            return;
        }
    }

    console.log("no changes")
    submitBtn.disabled = true;
}