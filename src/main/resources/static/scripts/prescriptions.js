
let productsInPrescription = [];
let allProducts = [];
let allCustomers = [];
let currentEditingProductIndex = -1;

const roleH5 = document.querySelector("#role");
const optionsNav = document.querySelector("#nav-options");
const prescriptionModal = new bootstrap.Modal("#prescription-modal");
const productSelectionModal = new bootstrap.Modal("#product-selection-modal");
const prescriptionForm = document.querySelector("#prescription-form");

document.addEventListener("DOMContentLoaded", async () => {
    
    allCustomers = await fetch("/api/clientes").then(r => r.json());
    const select = document.querySelector("#customer-select");
    const filterSelect = document.querySelector("#filter-customer");
    
    select.innerHTML = '<option value="" selected disabled>Seleccione un cliente</option>';
    filterSelect.innerHTML = '<option value="">Todos los clientes</option>';
    
    allCustomers.forEach(customer => {
        const name = `${customer.persona.primerNombre} ${customer.persona.primerApellido} ${customer.persona.dni || ""}`;
        
        const option = document.createElement("option");
        option.value = customer.idCliente;
        option.textContent = name;
        select.appendChild(option);
        
        const filterOption = document.createElement("option");
        filterOption.value = customer.idCliente;
        filterOption.textContent = name;
        filterSelect.appendChild(filterOption);
    });
    
    allProducts = await fetch("/api/productos").then(r => r.json());
    loadAvailableProducts(allProducts);
    
    await loadPrescriptions();
        
    document.querySelector("#search-prescription").addEventListener("input", searchPrescriptions);
    document.querySelector("#filter-customer").addEventListener("change", filterByCustomer);
    document.querySelector("#search-product-btn").addEventListener("click", searchAvailableProducts);
    document.querySelector("#search-product").addEventListener("keypress", (e) => {
        if (e.key === "Enter") searchAvailableProducts();
    });
    
    document.querySelector(".create").addEventListener("click", () => {
        document.querySelector("#prescription-modal-label").textContent = "Nueva Receta";
        prescriptionForm.reset();
        document.querySelector("#prescription-id").value = "";
        document.querySelector("#prescription-date").valueAsDate = new Date();
        productsInPrescription = [];
        renderPrescriptionProducts();
    });
    
    document.querySelector("#reset-btn").addEventListener("click", resetForm);
    document.querySelector("#add-product-btn").addEventListener("click", () => {
        currentEditingProductIndex = -1;
        productSelectionModal.show();
    });
    
    prescriptionForm.addEventListener("submit", handleFormSubmit);
    
     roleH5.textContent = userData.rol.nombreRol;
    
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
    

    document.querySelectorAll(".create").forEach(el => { 
        if(!auth.create) el.remove() 
    });
    
    document.querySelectorAll(".edit-btn").forEach(el => {
        if(!auth.mod) el.remove()
    });

    document.querySelectorAll(".delete-btn").forEach(el => {
        if(!auth.del) el.remove()
    });

    document.querySelector("#logout-btn").addEventListener("click", () => {
        window.localStorage.removeItem("userData");
        window.location.href = "/login";
    })
});


async function loadPrescriptions() {
    try {
        const response = await fetch("/api/recetas");
        const prescriptions = await response.json();
        const tableBody = document.querySelector("#prescriptions-table-body");
        tableBody.innerHTML = "";
        
        for (const prescription of prescriptions) {
            const customer = prescription.cliente;
            const customerName = customer ? `${customer.persona.primerNombre} ${customer.persona.primerApellido}` : "Cliente no encontrado";
            
            const prescriptionProducts = prescription.productos;
            const productsList = prescriptionProducts.map(p => `${p.producto.nombreProducto} (${p.cantidad})`).join(", ");
            
            const row = document.createElement("tr");
            row.innerHTML = `
                <td>${formatDate(prescription.fecha)}</td>
                <td>${customerName}</td>
                <td>${prescription.nombreMedico}</td>
                <td class="text-truncate-2" title="${productsList}">${productsList}</td>
            `;

            const tdBtnContainer = document.createElement("td");
            tdBtnContainer.classList.add("text-nowrap")
            const viewBtn = document.createElement("button");
            viewBtn.setAttribute("class","btn btn-sm btn-primary view-btn")
            viewBtn.innerHTML = '<i class="fas fa-eye"></i>'
            viewBtn.dataset.id = customer.idCliente;
            viewBtn.addEventListener("click", viewPrescription.bind(null, prescription.idReceta));
            tdBtnContainer.appendChild(viewBtn);

            if(auth.mod ) {
                tdBtnContainer.appendChild(document.createTextNode(" "));
                const btnEdit = document.createElement("button");
                btnEdit.setAttribute("class","btn btn-sm btn-warning edit-btn");
                btnEdit.innerHTML = '<i class="fas fa-edit"></i>'
                btnEdit.addEventListener("click", editPrescription.bind(null, prescription.idReceta));
                tdBtnContainer.appendChild(btnEdit);
            }

            if(auth.mod ) {
                tdBtnContainer.appendChild(document.createTextNode(" "));
                const btnDel = document.createElement("button");
                btnDel.setAttribute("class","btn btn-sm btn-danger delete-btn");
                btnDel.innerHTML = '<i class="fas fa-trash"></i>'
                btnDel.addEventListener("click", deletePrescription.bind(null, prescription.idReceta));
                tdBtnContainer.appendChild(btnDel);
            }

            row.appendChild(tdBtnContainer)
            tableBody.appendChild(row);
        }
    } catch (error) {
        showError("Error al cargar recetas", error.message);
    }
}

async function viewPrescription(id) {
    try {

        const prescription = await fetch(`/api/recetas/${id}`).then(r => r.json());
        const prescriptionProducts = prescription.productos;
        
        const customer = allCustomers.find(c => c.idCliente === prescription.idCliente);
        const customerName = customer ? `${customer.persona.primerNombre} ${customer.persona.primerApellido}` : "Cliente no encontrado";
        
        let productsTable = '<table class="table table-sm mt-3"><thead><tr><th>Producto</th><th>Cantidad</th><th>Indicaciones</th></tr></thead><tbody>';
        
        prescriptionProducts.forEach(p => {
            productsTable += `
                <tr>
                    <td>${p.producto.nombreProducto}</td>
                    <td>${p.cantidad}</td>
                    <td>${p.indicaciones || "N/A"}</td>
                </tr>
            `;
        });
        
        productsTable += "</tbody></table>";
        
        Swal.fire({
            title: `Receta #${prescription.idReceta}`,
            html: `
                <div class="text-start">
                    <p><strong>Fecha:</strong> ${formatDate(prescription.fecha)}</p>
                    <p><strong>Cliente:</strong> ${customerName}</p>
                    <p><strong>Médico:</strong> ${prescription.nombreMedico}</p>
                    <p><strong>Indicaciones:</strong></p>
                    <div class="border p-2 mb-3 bg-light">${prescription.descripcion?.replace(/\n/g, "<br>") || "N/A"}</div>
                    <p><strong>Productos:</strong></p>
                    ${productsTable}
                </div>
            `,
            confirmButtonText: "Cerrar",
            width: "800px"
        });
    } catch (error) {
        showError("Error al cargar receta", error.message);
    }
}

async function editPrescription(id) {
    try {


        const prescription = await fetch(`/api/recetas/${id}`).then(r => r.json());
        const prescriptionProducts = prescription.productos;
        
        document.querySelector("#prescription-modal-label").textContent = "Editar Receta";
        document.querySelector("#prescription-id").value = prescription.idReceta;
        document.querySelector("#prescription-date").value = prescription.fecha.split("T")[0];
        document.querySelector("#customer-select").value = prescription.idCliente;
        document.querySelector("#doctor-name").value = prescription.nombreMedico;
        document.querySelector("#prescription-description").value = prescription.descripcion || "";

        productsInPrescription = prescriptionProducts.map(p => ({
            producto: {
                idProducto: p.producto.idProducto,
                nombreProducto: p.producto.nombreProducto,
                descripcion: p.producto.descripcion,
            },
            cantidad: p.cantidad,
            indicaciones: p.indicaciones || ""
        }));
        
        renderPrescriptionProducts();
        prescriptionModal.show();
    } catch (error) {
        showError("Error al cargar receta para edición", error.message);
    }
}


async function deletePrescription(id) {
    try {
        const response = await fetch(`/api/recetas/verificar/eliminar/${id}`);
        const data = await response.json();
        
        if (!response.ok) throw new Error(data.msg);
        
        const prescription = await fetch(`/api/recetas/${id}`).then(r => r.json());
        const customer = prescription.cliente;
        const customerName = customer ? `${customer.persona.primerNombre} ${customer.persona.primerApellido} ${customer.persona.dni || ""}` : "Cliente no encontrado";
        
        Swal.fire({
            title: "Confirmar Eliminación",
            html: `¿Eliminar receta del <strong>${formatDate(prescription.fecha)}</strong> para <strong>${customerName}</strong>?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#dc3545",
            cancelButtonColor: "#6c757d",
            confirmButtonText: "Eliminar",
            cancelButtonText: "Cancelar"
        }).then(async (result) => {
            if (result.isConfirmed) {
                await fetch(`/api/recetas/eliminar/${id}`, { 
                    method: "DELETE" 
                });
                showSuccess("Receta eliminada correctamente");
                await loadPrescriptions();
            }
        });
    } catch (error) {
        const message = error.message.includes("inexistente") ? 
            "La receta tiene no existe." : 
            "Ocurrió un error al intentar eliminar la receta";
        showError("Error al eliminar", message);
    }
}

async function handleFormSubmit(e) {
    e.preventDefault();

    console.trace("submit=======")
    
    const submitBtn = document.querySelector("#submit-btn");
    submitBtn.disabled = true;
    submitBtn.innerHTML = '<span class="spinner-border spinner-border-sm"></span> Procesando...';
    
    try {
        if (productsInPrescription.length === 0) {
            throw new Error("Debe agregar al menos un producto a la receta");
        }
        
        const prescriptionData = {
            idReceta: document.querySelector("#prescription-id").value || 0,
            fecha: document.querySelector("#prescription-date").value,
            descripcion: document.querySelector("#prescription-description").value,
            nombreMedico: document.querySelector("#doctor-name").value,
            cliente: {
                idCliente: document.querySelector("#customer-select").value,
            },
            productos: productsInPrescription.map(item => ({
                producto: {
                    idProducto: item.producto.idProducto,
                },
                cantidad: item.cantidad,
                indicaciones: item.indicaciones
            }))
        };
        
        const isEdit = !!prescriptionData.idReceta;
        const endpoint = isEdit ? "/api/recetas/modificar" : "/api/recetas/agregar";
        const method = isEdit ? "PUT" : "POST";
        
        const response = await fetch(endpoint, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(prescriptionData)
        });
        
        const data = await response.json();
        if (!response.ok) throw new Error(data.msg || "Error en la solicitud");
        

        showSuccess(isEdit ? "Receta actualizada correctamente" : "Receta creada correctamente");
        prescriptionModal.hide();
        await loadPrescriptions();
        
    } catch (error) {
        showError("Error al guardar", error.message);
    } finally {
        submitBtn.disabled = false;
        submitBtn.innerHTML = '<i class="fas fa-save me-1"></i> Guardar';
    }
}

// Funciones auxiliares para productos
function loadAvailableProducts(products) {
    const tbody = document.querySelector("#available-products-body");
    tbody.innerHTML = "";
    
    products.forEach(product => {
        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${product.nombreProducto}</td>
            <td class="text-truncate" style="max-width: 200px;" title="${product.descripcion}">${product.descripcion}</td>`;

        const td = document.createElement("td");
    
        const button = document.createElement("button");
        button.className = "btn btn-sm btn-primary";
        button.dataset.id = product.idProduct;
        button.innerHTML = '<i class="fas fa-check"></i>';
        button.addEventListener("click", () => selectProduct(product));

        td.appendChild(button);
        tr.appendChild(td)

        tbody.appendChild(tr);
    });
}

function selectProduct(product) {
    if (currentEditingProductIndex >= 0) {
        productsInPrescription[currentEditingProductIndex].product = product;
    } else {

        if( productsInPrescription.some( p => p.producto.idProducto === product.producto.idProducto))
        productsInPrescription.push({
            producto: product,
            cantidad: 1,
            indicaciones: ""
        });
    }
    
    renderPrescriptionProducts();
    productSelectionModal.hide();
    currentEditingProductIndex = -1;
}

function renderPrescriptionProducts() {
    const tbody = document.querySelector("#prescription-products-body");
    tbody.innerHTML = "";
    
    productsInPrescription.forEach((item, index) => {

        const tr = document.createElement("tr");
        tr.innerHTML = `<td>${item.producto.nombreProducto}</td>`;
        console.log(productsInPrescription)

        const quantityTd = document.createElement("td");
            const quantityInput = document.createElement("input");
            quantityInput.type = "number";
            quantityInput.min = "1";
            quantityInput.value = item.cantidad;
            quantityInput.className = "form-control form-control-sm";
            quantityInput.dataset.index = index;
            quantityInput.addEventListener("input", (e) => {
                productsInPrescription[index].cantidad = parseInt(e.target.value);
            });
    
        quantityTd.appendChild(quantityInput);
    
        const instructionsTd = document.createElement("td");
            const instructionsTextarea = document.createElement("textarea");
            instructionsTextarea.className = "form-control form-control-sm";
            instructionsTextarea.rows = 1;
            instructionsTextarea.value = item.indicaciones || "";
            instructionsTextarea.addEventListener("input", (e) => {
                productsInPrescription[index].indicaciones = e.target.value;
                console.log(productsInPrescription);
            });
        
        instructionsTd.appendChild(instructionsTextarea);
    
        const actionsTd = document.createElement("td");
        actionsTd.classList.add("text-nowrap");
            const editBtn = document.createElement("button");
            editBtn.type = "button"
            editBtn.className = "btn btn-sm btn-outline-warning me-1";
            editBtn.innerHTML = `<i class="fas fa-edit"></i>`;
            editBtn.addEventListener("click", () => {
                currentEditingProductIndex = index;
                productSelectionModal.show();
            });

            const deleteBtn = document.createElement("button");
            deleteBtn.type = "button"
            deleteBtn.className = "btn btn-sm btn-outline-danger";
            deleteBtn.innerHTML = `<i class="fas fa-trash"></i>`;
            deleteBtn.addEventListener("click", () => {
                productsInPrescription.splice(index, 1);
                renderPrescriptionProducts();
            });
    
        actionsTd.appendChild(editBtn);
        actionsTd.appendChild(deleteBtn);
    
        tr.appendChild(quantityTd);
        tr.appendChild(instructionsTd);
        tr.appendChild(actionsTd);
        
        tbody.appendChild(tr);
    });
}


function searchPrescriptions() {
    const searchTerm = document.querySelector("#search-prescription").value.trim().toLowerCase();
    const customerFilter = document.querySelector("#filter-customer").value;
    
    const rows = document.querySelectorAll("#prescriptions-table-body tr");
    
    rows.forEach(row => {
        const customerName = row.cells[1].textContent.toLowerCase();
        const doctorName = row.cells[2].textContent.toLowerCase();
        const productsList = row.cells[3].textContent.toLowerCase();
        const matchesCustomer = !customerFilter || row.cells[1].querySelector('option[value="'+customerFilter+'"]');
        const matchesSearch = !searchTerm || 
            customerName.includes(searchTerm) || 
            doctorName.includes(searchTerm) || 
            productsList.includes(searchTerm);
        
        row.style.display = (matchesCustomer && matchesSearch) ? "" : "none";
    });
}

function searchAvailableProducts() {
    const searchTerm = document.querySelector("#search-product").value.trim().toLowerCase();
    
    if (!searchTerm) {
        loadAvailableProducts(allProducts);
        return;
    }
    
    const filteredProducts = allProducts.filter(product => 
        product.productName.toLowerCase().includes(searchTerm) ||
        product.description.toLowerCase().includes(searchTerm)
    );
    
    loadAvailableProducts(filteredProducts);
}

function filterByCustomer() {
    searchPrescriptions();
}

function resetForm() {
    prescriptionForm.reset();
    document.querySelector("#prescription-date").valueAsDate = new Date();
    productsInPrescription = [];
    renderPrescriptionProducts();
}

function formatDate(dateString) {
    const options = { year: "numeric", month: "short", day: "numeric" };
    return new Date(dateString).toLocaleDateString("es-HN", options);
}

function showSuccess(message) {
    Swal.fire({
        icon: "success",
        title: "Éxito",
        text: message,
        confirmButtonColor: "#198754",
        confirmButtonText: "Aceptar"
    });
}

function showError(title, message) {
    Swal.fire({
        icon: "error",
        title: title,
        text: message,
        confirmButtonText: "Entendido"
    });
}