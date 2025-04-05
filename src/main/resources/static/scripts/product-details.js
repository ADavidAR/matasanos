window.addEventListener("DOMContentLoaded", async () => {
    let idSucursal = 1; //que lo obbtenga de localstorage
    const params = new URLSearchParams(window.location.search);
    const idProduct = params.get("idProducto");

    const response = await fetch(`/api/productos/${idProduct}?idSucursal=${idSucursal}`);
    const details = await response.json();
    console.log(details);

    document.getElementById("view-title").textContent = `${details.nombreProducto}`;

    const section = document.querySelector("#detail-container");
    section.innerHTML = "";

    //carga los detalles
    const column = document.createElement("div");
    column.classList.add("card");
    column.classList.add("card-details");
    column.innerHTML = `

          <div class="card-header">
            <h2 id="productName">${details.nombreProducto}</h2>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item">Inventario: ${details.inventario}</li>
            <li class="list-group-item">Producto ID: ${details.idProducto}</li>
            <li class="list-group-item">Descripción: ${details.descripcion}</li>
            <li class="list-group-item">Precio de venta: ${details.precioVenta}</li>
            <li class="list-group-item">Fecha de Vencimiento: ${details.fechaVencimiento}</li>
            <li class="list-group-item" id="productRestriction"></li>
            <li class="list-group-item">Categoria: ${details.categoria.nombreCategoria}</li>
            <li class="list-group-item">Departamento: ${details.categoria.departamento.nombreDepartamento}</li>
          </ul>
      `;
      section.appendChild(column);

      let productRest = document.getElementById("productRestriction");

      if (details.ventaLibre === true) {
          productRest.textContent = "Producto de venta libre";
      } else {
          productRest.textContent = "No es un producto de venta libre";
      }


      const modal = document.getElementById("productoModal");
      const form = document.getElementById("productoForm");
      const categorySelect = document.getElementById("categoria");
      const providerSelect = document.getElementById("proveedor");

      let productoOriginal = null;

      modal.addEventListener("show.bs.modal", async function (event) {
          const button = event.relatedTarget;
          const mode = button.getAttribute("data-mode");
          const modalTitle = modal.querySelector(".modal-title");

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


          if (mode === "crear") {
              modalTitle.textContent = "Crear nuevo producto";
              form.reset();
              productoOriginal = null;
          } else if (mode === "actualizar") {
              modalTitle.textContent = "Actualizar producto";

              productoOriginal = {
                  nombre: details.nombreProducto,
                  descripcion: details.descripcion,
                  precio: details.precioVenta,
                  fechaVencimiento: details.fechaVencimiento,
                  ventaLibre: details.ventaLibre,
                  impuesto: details.impuesto,
                  idCategoria: details.categoria.idCategoria,
                  idProveedor: details.proveedor.idProveedor,
              };

              document.getElementById("nombre").value = productoOriginal.nombre;
              document.getElementById("descripcion").value = productoOriginal.descripcion;
              document.getElementById("precio").value = productoOriginal.precio;
              document.getElementById("fechaVencimiento").value = productoOriginal.fechaVencimiento;
              document.getElementById("ventaLibre").checked = productoOriginal.ventaLibre;
              document.getElementById("impuesto").value = productoOriginal.impuesto;
              categorySelect.value = productoOriginal.idCategoria;
              providerSelect.value = productoOriginal.idProveedor;
          }
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

          if (productoOriginal) {
              const sinCambios = Object.keys(nuevoProducto).every(key => nuevoProducto[key] === productoOriginal[key]);
              if (sinCambios) {
                  alert("No se detectaron cambios en el formulario.");
                  return;
              }

              console.log("Producto actualizado:", nuevoProducto);

          } else {
              console.log("Creando nuevo producto:", nuevoProducto);

          }

          bootstrap.Modal.getInstance(modal).hide();

          location.reload();
      });


      const reportsModal = document.getElementById("reportesModal");
      const tableBody = document.getElementById("table-body")

      //llenado del modal que muestra los movimientos del producto
      reportsModal.addEventListener("show.bs.modal", async function (event) {
          const res = await fetch(`/api/productos/reportes/${idProduct}/${idSucursal}`);
          const reports = await res.json();
          console.log(reports);

          tableBody.innerHTML = "";
          let i = 0;

          reports.forEach(p => {
                const tr = document.createElement("tr");
                i++;
                tr.innerHTML = `
                        <tr>
                            <th scope="row">${i}</th>
                            <td>${p.fecha}</td>
                            <td>${p.tipoMovimiento.nombre}</td>
                            <td>${p.cantidad}</td>
                            <td>cantidad total</td>
                            <td>${p.referencia}</td>
                        </tr>
                `;
                tableBody.appendChild(tr);
          });
      });

})