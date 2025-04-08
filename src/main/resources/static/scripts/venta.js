document.addEventListener("DOMContentLoaded", function () {
  let productos = []; // Guardar los productos para filtrar después
  let carrito = JSON.parse(localStorage.getItem('carrito')) || []; // Array para almacenar los 
  //tomo el valor de la id sucursal
  const stored = localStorage.getItem("userData");  
  const usuario = JSON.parse(stored);
  const idSucursal = usuario?.empleado?.sucursal?.idSucursal;
  console.log(idSucursal);

  fetch(`api/ventas/${idSucursal}`)
    .then(response => response.json())
    .then(data => {
      productos = data; // Guardamos los productos obtenidos
      mostrarProductos(productos); // Mostramos todos los productos al cargar
    })
    .catch(error => console.log("Error al obtener productos:", error));

  // Filtrar productos en tiempo real
  document.getElementById("busqueda-input").addEventListener("input", function () {
    let searchText = this.value.toLowerCase(); // Obtener el texto en minúsculas
    let productosFiltrados = productos.filter(producto =>
      producto.nombreProducto.toLowerCase().includes(searchText)
    );
    mostrarProductos(productosFiltrados); // Mostrar solo los productos filtrados
  });

  // Función para mostrar los productos
  function mostrarProductos(lista) {
    const containerBody = document.getElementById("productos-container");
    containerBody.innerHTML = ""; // Limpiar el contenedor

    lista.forEach((producto) => {
let card = `<div class="col-lg-3 col-md-4 col-sm-6 mb-4 producto-item" data-id="${producto.idProducto}">
                  <div class="card product-card">
                    <div class="card-body">
                      <h5 class="card-title">${producto.nombreProducto}</h5>
                      <p class="card-text">Venta Libre: ${producto.ventaLibre ? 'Sí' : 'No'}</p>
                      <div class="d-flex justify-content-between align-items-center">
                        <span class="badge ${producto.inventario > 0 ? 'badge-disponible bg-success' : 'bg-secondary'}">
                          ${producto.inventario > 0 ? producto.inventario + ' Disponibles' : 'Agotado'}
                        </span>
                        <h5 class="text-primary">L.${producto.precioVenta.toFixed(2)}</h5>
                      </div>
                      <div class="mt-4">
                          <div class="input-group mb-3" style="max-width: 150px;">
                              <button class="btn btn-outline-secondary decrementar-btn" type="button">-</button>
                              <input type="number" class="form-control text-center cantidad-input" value="1" min="1" max="${producto.inventario}">
                              <button class="btn btn-outline-secondary incrementar-btn" type="button">+</button>
                          </div>
                          <button class="btn btn-primary btn-lg w-100 añadir-al-carrito-btn" ${producto.inventario <= 0 ? 'disabled' : ''}>
                              <i class="fas fa-cart-plus"></i> Añadir al carrito
                          </button>
                      </div>
                    </div>
                  </div>
                </div>`;
      containerBody.innerHTML += card;
    });

    // una vez cargados los elementos, que se agreguen sus funcionalidades
    configurarEventosCarrito();
    cargarDepartamentos();
    cargarCategorias(); 
  }

  // Función para configurar los eventos de los productos
  function configurarEventosCarrito() {

    // Botones de incremento
    document.querySelectorAll('.incrementar-btn').forEach(btn => {
      btn.addEventListener('click', function () {
        const input = this.parentElement.querySelector('.cantidad-input');
        const max = parseInt(input.getAttribute('max'));
        let value = parseInt(input.value);
        if (value < max) {
          input.value = value + 1;
        }
      });
    });

    // Botones de decremento
    document.querySelectorAll('.decrementar-btn').forEach(btn => {
      btn.addEventListener('click', function () {
        const input = this.parentElement.querySelector('.cantidad-input');
        let value = parseInt(input.value);
        if (value > 1) {
          input.value = value - 1;
        }
      });
    });

    // Botones de añadir al carrito
    document.querySelectorAll('.añadir-al-carrito-btn').forEach(btn => {
      btn.addEventListener('click', function () {
        const card = this.closest('.producto-item');
        const productId = card.getAttribute('data-id');
        const cantidadInput = card.querySelector('.cantidad-input');
        const cantidad = parseInt(cantidadInput.value);

        // Buscar el producto en el array de productos
        const producto = productos.find(p => p.idProducto == productId);

        if (producto) {
          // Verificar si el producto ya está en el carrito
          const itemExistente = carrito.find(item => item.idProducto == productId);

          if (itemExistente) {
            // Actualizar cantidad si ya existe
            itemExistente.cantidad += cantidad;
          } else {
            // Agregar nuevo item al carrito
            carrito.push({
              ...producto,
              cantidad: cantidad,
              idSucursal: idSucursal
            });
          }

          console.log(carrito.id_sucursal);
          localStorage.setItem('carrito', JSON.stringify(carrito));
          // Mostrar notificación
          alert(`${cantidad} ${producto.nombreProducto} añadido(s) al carrito`);
          cantidadInput.value = 1;
        }
      });
    });
  }

  function cargarCategorias() {
    fetch("api/ventas/categorias")
      .then(response => response.json())
      .then(categorias => {

        const menu = document.getElementById("categorias-menu")
        categorias.forEach(categoria => {
          const item = document.createElement('li');
          const link = document.createElement('a');
          link.className = 'dropdown-item';
          link.textContent = categoria.nombreCategoria;

          link.addEventListener('click', () => {
            filtrarPorCategoria(categoria.nombreCategoria);
            configurarEventosCarrito();
          });

          item.appendChild(link);
          menu.appendChild(item);
        });
      })
      .catch(error => console.error("Error al obtener categorías:", error));
  }

  function cargarDepartamentos() {
    fetch("api/ventas/departamentos")
      .then(response => response.json())
      .then(departamentos => {
        const menu = document.getElementById('departamentos-menu');

        departamentos.forEach(depto => {
          const item = document.createElement('li');
          const link = document.createElement('a');
          link.className = 'dropdown-item';
          link.textContent = depto.nombreDepartamento;

          link.addEventListener('click', () => {
            filtrarPorDepartamento(depto.nombreDepartamento);
            configurarEventosCarrito();
          });

          item.appendChild(link);
          menu.appendChild(item);
        });
      });
  }

  // Función para filtrar productos por departamento
  function filtrarPorDepartamento(nombreDepartamento) {
    const container = document.getElementById('productos-container');
    container.innerHTML = ''; // Limpiar contenedor

    const productosFiltrados = productos.filter(
      producto => producto.categoria.departamento.nombreDepartamento === nombreDepartamento
    );

    productosFiltrados.forEach(producto => {
      let card = `<div class="col-lg-3 col-md-4 col-sm-6 mb-4 producto-item" data-id="${producto.idProducto}">
                        <div class="card product-card">
                          <div class="card-body">
                            <h5 class="card-title">${producto.nombreProducto}</h5>
                            <p class="card-text">Venta Libre: ${producto.ventaLibre ? 'Sí' : 'No'}</p>
                            <div class="d-flex justify-content-between align-items-center">
                              <span class="badge ${producto.inventario > 0 ? 'badge-disponible bg-success' : 'bg-secondary'}">
                                ${producto.inventario > 0 ? producto.inventario + ' Disponibles' : 'Agotado'}
                              </span>
                              <h5 class="text-primary">L.${producto.precioVenta.toFixed(2)}</h5>
                            </div>
                            <div class="mt-4">
                                <div class="input-group mb-3" style="max-width: 150px;">
                                    <button class="btn btn-outline-secondary decrementar-btn" type="button">-</button>
                                    <input type="number" class="form-control text-center cantidad-input" value="1" min="1" max="${producto.inventario}">
                                    <button class="btn btn-outline-secondary incrementar-btn" type="button">+</button>
                                </div>
                                <button class="btn btn-primary btn-lg w-100 añadir-al-carrito-btn" ${producto.inventario <= 0 ? 'disabled' : ''}>
                                    <i class="fas fa-cart-plus"></i> Añadir al carrito
                                </button>
                            </div>
                          </div>
                        </div>
                      </div>`;
      container.innerHTML += card;

    });
  }

  // Función para filtrar productos por departamento
  function filtrarPorCategoria(nombreCategoria) {
    const container = document.getElementById('productos-container');
    container.innerHTML = ''; // Limpiar contenedor

    const productosFiltrados = productos.filter(
      producto => producto.categoria.nombreCategoria === nombreCategoria
    );

    productosFiltrados.forEach(producto => {
      let card = `<div class="col-lg-3 col-md-4 col-sm-6 mb-4 producto-item" data-id="${producto.idProducto}">
                        <div class="card product-card">
                          <div class="card-body">
                            <h5 class="card-title">${producto.nombreProducto}</h5>
                            <p class="card-text">Venta Libre: ${producto.ventaLibre ? 'Sí' : 'No'}</p>
                            <div class="d-flex justify-content-between align-items-center">
                              <span class="badge ${producto.inventario > 0 ? 'badge-disponible bg-success' : 'bg-secondary'}">
                                ${producto.inventario > 0 ? producto.inventario + ' Disponibles' : 'Agotado'}
                              </span>
                              <h5 class="text-primary">L.${producto.precioVenta.toFixed(2)}</h5>
                            </div>
                            <div class="mt-4">
                                <div class="input-group mb-3" style="max-width: 150px;">
                                    <button class="btn btn-outline-secondary decrementar-btn" type="button">-</button>
                                    <input type="number" class="form-control text-center cantidad-input" value="1" min="1" max="${producto.inventario}">
                                    <button class="btn btn-outline-secondary incrementar-btn" type="button">+</button>
                                </div>
                                <button class="btn btn-primary btn-lg w-100 añadir-al-carrito-btn" ${producto.inventario <= 0 ? 'disabled' : ''}>
                                    <i class="fas fa-cart-plus"></i> Añadir al carrito
                                </button>
                            </div>
                          </div>
                        </div>
                      </div>`;
      container.innerHTML += card;

    });
  }
});

window.addEventListener("DOMContentLoaded", async () => {
  const roleH5 = document.querySelector("#role");
  const optionsNav = document.querySelector("#nav-options");
  
  let userData = JSON.parse(localStorage.getItem("userData"));

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
  })

  document.querySelector("#logout-btn").addEventListener("click", () => {
      window.localStorage.removeItem("userData");
      window.location.href = "/login";
  })
})