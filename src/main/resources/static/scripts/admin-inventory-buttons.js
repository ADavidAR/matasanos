const modal = document.getElementById("productoModal");
  const form = document.getElementById("productoForm");
  const categorySelect = document.getElementById("categoria");
  const providerSelect = document.getElementById("proveedor");


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
      productoOriginal = null;

  });

  form.addEventListener("submit", async function (e) {
      e.preventDefault();

      const nuevoProducto = {
          nombre: document.getElementById("nombre").value,
          descripcion: document.getElementById("descripcion").value,
          precio: parseFloat(document.getElementById("precio").value),
          fechaVencimiento: document.getElementById("fechaVencimiento").value,
          ventaLibre: document.getElementById("ventaLibre").checked,
          impuesto: parseFloat(document.getElementById("impuesto").value),
          idCategoria: parseInt(categorySelect.value),
          idProveedor: parseInt(providerSelect.value),
          idSucursal: parseInt(idSucursal)
      };

      bootstrap.Modal.getInstance(modal).hide();

      location.reload();
});