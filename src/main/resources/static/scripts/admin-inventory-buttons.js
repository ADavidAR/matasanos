const modal = document.getElementById("productoModal");
const modalCategory = document.getElementById("categoriaModal");
const modalDepartment = document.getElementById("departamentoModal");
const modalProveedor = document.getElementById("proveedorModal");

const form = document.getElementById("productoForm");
const formCategory = document.getElementById("categoriaForm");
const formDepartment = document.getElementById("departamentoForm");
const formProveedor = document.getElementById("proveedorForm");

const categorySelect = document.getElementById("categoria");
const providerSelect = document.getElementById("proveedor");
const departmentSelect = document.getElementById("departamento");


//event listener para el modal de crear productos
modal.addEventListener("show.bs.modal", async function (event) {
  const button = event.relatedTarget;
  const mode = button.getAttribute("data-mode");

  //carga a los proveedores en el select
  const res = await fetch("/api/proveedores");
        const providers = await res.json();
        console.log(providers);
        providerSelect.innerHTML = '<option value="">Seleccione un proveedor</option>';
        providers.forEach(p => {
            const option = document.createElement("option");
            option.value = p.idProveedor;
            option.textContent = p.razonSocial;
            providerSelect.appendChild(option);
  });

  //carga a las categorias en el select
  const res2 = await fetch("/api/categorias");
        const category = await res2.json();
        console.log(category);
        categorySelect.innerHTML = '<option value="">Seleccione una categoría</option>';
        category.forEach(c => {
            const option = document.createElement("option");
            option.value = c.idCategoria;
            option.textContent = c.nombreCategoria;
            categorySelect.appendChild(option);
  });

  form.reset();

});

//event listener para el modal de crear categoria
modalCategory.addEventListener("show.bs.modal", async function (event) {
  const button = event.relatedTarget;

  //carga los departamentos en el select
        const res3 = await fetch("/api/departamentos");
              const departments = await res3.json();
              console.log(departments);
              departmentSelect.innerHTML = '<option value="">Seleccione un departamento</option>';
              departments.forEach(c => {
                  const option = document.createElement("option");
                  option.value = c.idDepartamento;
                  option.textContent = c.nombreDepartamento;
                  departmentSelect.appendChild(option);
        });

  form.reset();

});

//funcion para manejar los submits
async function handleFormSubmit(formId, dataObj, apiUrl) {
  event.preventDefault();

  try {
    const response = await fetch(apiUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(dataObj)
    });

    if (!response.ok) {
      throw new Error(`Error al guardar los datos en ${apiUrl}`);
    }

    alert("Guardado correctamente");

    bootstrap.Modal.getInstance(document.getElementById(`${formId}Modal`)).hide();

    location.reload();

  } catch (error) {
    console.error("Hubo un error:", error);
    alert("No se pudo guardar la información");
  }
}

//departamento submit
formDepartment.addEventListener("submit", function (e) {
  const nameDepto = document.getElementById("nombre-departamento").value;

  const newDpto = {
    nombreDepartamento: nameDepto
  };

  handleFormSubmit("departamento", newDpto, "/api/departamentos");
});

//categoria submit
formCategory.addEventListener("submit", function (e) {
  const nameCat = document.getElementById("nombre-categoria").value;
  const idDepartamento = document.getElementById("departamento").value;

  const newCat = {
    nombreCategoria: nameCat,
    departamento: { idDepartamento: idDepartamento }
  };

  handleFormSubmit("categoria", newCat, "/api/categorias");
});

form.addEventListener("submit", function (e) {
  const nameCat = document.getElementById("nombre-categoria").value;
  const idDepartamento = document.getElementById("departamento").value;

  const newCat = {
    nombreCategoria: nameCat,
    departamento: { idDepartamento: idDepartamento }
  };

  handleFormSubmit("producto", newCat, "/api/categorias");
});

/*
//categoria submit
formCategory.addEventListener("submit", function (e) {
  const nameCat = document.getElementById("nombre-categoria").value;
  const idDepartamento = document.getElementById("departamento").value;

  const newCat = {
    nombreCategoria: nameCat,
    departamento: { idDepartamento: idDepartamento }
  };

  handleFormSubmit("categoria", newCat, "/api/categorias");
});

form.addEventListener("submit", function (e) {
  const nameCat = document.getElementById("nombre-categoria").value;
  const idDepartamento = document.getElementById("departamento").value;

  const newCat = {
    nombreCategoria: nameCat,
    departamento: { idDepartamento: idDepartamento }
  };

  handleFormSubmit("producto", newCat, "/api/categorias");
});*/