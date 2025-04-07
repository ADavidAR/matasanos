window.addEventListener("DOMContentLoaded", async () => {
    let idSucursal = 1; //que lo obbtenga de localstorage
    const idUsuario = 1;
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
            <div id="admin-del-upd-buttons">
                <button type="button" class="btn btn-primary m-2" data-bs-toggle="modal" data-bs-target="#actualizarModal" data-mode="actualizar">Actualizar</button>
                <button type="button" class="btn btn-danger m-2" id="eliminarProductoBtn">Eliminar</button>
            </div>
          </div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item" id="li-inventario">Inventario: ${details.inventario}</li>
            <li class="list-group-item">Producto ID: ${details.idProducto}</li>
            <li class="list-group-item">Descripción: ${details.descripcion}</li>
            <li class="list-group-item">Precio de venta: ${details.precioVenta}</li>
            <li class="list-group-item">Precio con descuento: ${details.precioDescuento}</li>
            <li class="list-group-item">Costo de venta: ${details.costoVenta}</li>
            <li class="list-group-item">Fecha de Vencimiento: ${details.fechaVencimiento}</li>
            <li class="list-group-item" id="productRestriction"></li>
            <li class="list-group-item">Categoria: ${details.categoria.nombreCategoria}</li>
            <li class="list-group-item">Departamento: ${details.categoria.departamento.nombreDepartamento}</li>
            <li class="list-group-item">Fecha de creacion: ${details.fechaCreacion}</li>
            <li class="list-group-item">Fecha de modificacion: ${details.fechaModificacion}</li>
            <li class="list-group-item" type="hidden">Usuario ID creacion: ${details.idUsuarioCreacion}</li>
          </ul>
      `;
      section.appendChild(column);

      let productRest = document.getElementById("productRestriction");

      if (details.ventaLibre === true) {
          productRest.textContent = "Producto de venta libre";
      } else {
          productRest.textContent = "No es un producto de venta libre";
      }

      const liInventory = document.getElementById("li-inventario");
      if (details.inventario > 0) {
          liInventory.classList.add("list-group-item-success");
      } else {
          liInventory.classList.add("list-group-item-danger");
      }


      const modal = document.getElementById("actualizarModal");
      const categorySelect = document.getElementById("categoria-actualizar");
      const providerSelect = document.getElementById("proveedor-actualizar");


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




              modalTitle.textContent = "Actualizar producto";

              productoOriginal = {
                  nombre: details.nombreProducto,
                  descripcion: details.descripcion,
                  precio: details.precioVenta,
                  precioDescuento: details.precioDescuento,
                  costoVenta: details.costoVenta,
                  fechaVencimiento: details.fechaVencimiento,
                  ventaLibre: details.ventaLibre,
                  impuesto: details.impuesto,
                  idCategoria: details.categoria.idCategoria,
                  idProveedor: details.proveedor.idProveedor,
                  fechaCreacion: details.fechaCreacion,
                  fechaModificacion: details.fechaModificacion,
              };

              document.getElementById("nombre-actualizar").value = productoOriginal.nombre;
              document.getElementById("descripcion-actualizar").value = productoOriginal.descripcion;
              document.getElementById("precio-actualizar").value = productoOriginal.precio;
              document.getElementById("precio-desc-actualizar").value = productoOriginal.precioDescuento;
              document.getElementById("costo-actualizar").value = productoOriginal.costoVenta;
              document.getElementById("fechaVencimiento-actualizar").value = productoOriginal.fechaVencimiento;
              document.getElementById("ventaLibre-actualizar").checked = productoOriginal.ventaLibre;
              document.getElementById("impuesto-actualizar").value = productoOriginal.impuesto;
              categorySelect.value = productoOriginal.idCategoria;
              providerSelect.value = productoOriginal.idProveedor;
              document.getElementById("fecha-cre-actualizar").value = productoOriginal.fechaCreacion;
              document.getElementById("fecha-mod-actualizar").value = productoOriginal.fechaModificacion;

      });

      const updateForm = document.getElementById("actualizarForm");
      let productoOriginal = null;

      updateForm.addEventListener("submit", async function (e) {
          e.preventDefault();

          const mCurrentDateL = new Date();
          const mCurrentDate = mCurrentDateL.toISOString().split('T')[0];

          const newProd = {
              nombreProducto: document.getElementById("nombre-actualizar").value,
              descripcion: document.getElementById("descripcion-actualizar").value,
              precioVenta: parseInt(document.getElementById("precio-actualizar").value),
              fechaVencimiento: document.getElementById("fechaVencimiento-actualizar").value,
              ventaLibre: document.getElementById("ventaLibre-actualizar").checked,
              precioDescuento: parseInt(document.getElementById("precio-desc-actualizar").value),
              impuesto: parseInt(document.getElementById("impuesto-actualizar").value),
              fechaCreacion: document.getElementById("fecha-cre-actualizar").value,
              fechaModificacion: mCurrentDate,
              costoVenta: parseInt(document.getElementById("costo-actualizar").value),
              categoria: { idCategoria: details.categoria.idCategoria },
              proveedor: { idProveedor: details.proveedor.idProveedor },
              inventario: null,
              idUsuarioCreacion: parseInt(document.getElementById("usu-cre-actualizar").value),
              idUsuarioModificacion: idUsuario

          };

          if (productoOriginal) {
              const sinCambios = Object.keys(newProd).every(key => newProd[key] === productoOriginal[key]);
              if (sinCambios) {
                  alert("No se detectaron cambios en el formulario.");
                  return;
              }
              console.log("Producto actualizado:", newProd);
          }

          try {
              const response = await fetch(`/api/productos/${idProduct}`, {
                  method: "PUT",
                  headers: {
                      "Content-Type": "application/json"
                  },
                  body: JSON.stringify(newProd)
              });

              if (response.ok) {
                  alert("Producto actualizado exitosamente");
                  bootstrap.Modal.getInstance(modal).hide();
                  location.reload();
              } else if (response.status === 404) {
                  alert("Producto no encontrado");
              } else {
                  alert("Error al actualizar el producto");
              }
          } catch (error) {
              console.error("Error en la solicitud:", error);
              alert("Hubo un problema al actualizar el producto.");
          }

      });


      const reportsModal = document.getElementById("reportesModal");
      const tableBody = document.getElementById("table-body");

      //llenado del modal que muestra los reportes/movimientos del producto
      reportsModal.addEventListener("show.bs.modal", async function (event) {
          const res = await fetch(`/api/productos/reportes/${idProduct}/${idSucursal}`);
          const reports = await res.json();
          //reports.reverse();
          console.log(reports);

          document.getElementById("reports-modal-title").textContent = `${details.nombreProducto}`;

          tableBody.innerHTML = "";
          let i = 0;
          let qty = 0;
          let qtyAr = [];

          reports.reverse().forEach((p, index) => {
                if (index === 0) {
                      qty = p.cantidad;
                 } else {
                      qty += (p.cantidad * p.tipoMovimiento.factor);
                 }
                qtyAr.push(qty);
          });
          qtyAr.reverse();


          reports.reverse().forEach((p, index) => {
              const tr = document.createElement("tr");
              i++;
              let sign;

              if(p.tipoMovimiento.factor === -1) {
                tr.classList.add("table-warning");
                sign = "-";
              } else {
                tr.classList.add("table-success");
                sign = "+";
              }

                tr.innerHTML = `
                        <tr>
                            <th scope="row">${i}</th>
                            <td>${p.fecha}</td>
                            <td>${p.tipoMovimiento.nombre}</td>
                            <td>${sign}${p.cantidad}</td>
                            <td>${qtyAr[index]}</td>
                            <td>${p.referencia}</td>
                        </tr>
                `;
                tableBody.appendChild(tr);
          });
      });


        /*const providersTableModal = document.getElementById("proveedoresModal");
        const tableBodyP = document.getElementById("table-body-proveedores")

      //llenado del modal que muestra los proveedores
        providersTableModal.addEventListener("show.bs.modal", async function (event) {
            const res = await fetch("/api/proveedores");
            const providers = await res.json();
            console.log(providers);

            document.getElementById("reports-modal-title").textContent = "Proveedores";

            tableBodyP.innerHTML = "";

            providers.forEach((p) => {
                const tr = document.createElement("tr");

                  tr.innerHTML = `
                          <tr>
                              <th scope="row">${p.idProveedor}</th>
                              <td>${p.razonSocial}</td>
                              <td>${p.contacto}</td>
                              <td>${p.rtnContacto}</td>
                              <td>${p.telefono}</td>
                              <td>${p.correo}</td>
                              <td>${p.direccion}</td>
                          </tr>
                  `;
                  tableBodyP.appendChild(tr);
            });
        });*/


        //eliminacion de producto
        eliminarProductoBtn.addEventListener("click", async () => {
                if (confirm("¿Está seguro de que deseas eliminar este producto?")) {
                        const eliminarResponse = await fetch(`/api/productos/${idProduct}`, {
                            method: 'DELETE',
                        });
                        if (eliminarResponse.ok) {
                            alert("Producto eliminado con éxito.");
                            window.location.href = "/inventory";
                        } else {
                            alert("Hubo un problema al eliminar el producto.");
                        }
                        console.error("Error al eliminar el producto:", error);
                        alert("Error al eliminar el producto.");
                }
            });

})

