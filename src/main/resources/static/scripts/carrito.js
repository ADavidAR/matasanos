document.addEventListener("DOMContentLoaded", function () {

  let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
  mostrarProductosCarrito(carrito);

  function mostrarProductosCarrito(listaCarrito) {
    const carritoTable = document.getElementById("carrito-body");
    carritoTable.innerHTML = "";

    listaCarrito.forEach(producto => {
      let contenido = `              
        <tr class="detalle-producto" data-id="${producto.idProducto}">
          <td>${producto.idProducto}</td>
          <td>
            ${producto.nombreProducto}
          </td>
          <td>${producto.precioVenta}</td>
          <td>
            <input type="number" class="form-control quantity-input" value="${producto.cantidad}" min="1" data-id="${producto.idProducto}">
          </td>
          <td class="subtotal">${(producto.cantidad * producto.precioVenta).toFixed(2)}</td>
          <td class="text-center action-buttons">

            <button class="btn btn-sm btn-outline-danger">
              <i class="bi bi-trash"></i>
            </button>
          </td>
        </tr>              
                `
      carritoTable.innerHTML += contenido;
      
    });

    agregarEventosCantidad();
    eliminarProducto();
    deshabilitarBoton()
    }

    function agregarEventosCantidad() {
      const inputsCantidad = document.querySelectorAll(".quantity-input");
    
      inputsCantidad.forEach(input => {
        input.addEventListener("input", (e) => {
          const id = parseInt(e.target.dataset.id);
          const nuevaCantidad = parseInt(e.target.value);
    
          const producto = carrito.find(p => p.idProducto === id);
          if (!producto || isNaN(nuevaCantidad) || nuevaCantidad < 1) return;
    
          producto.cantidad = nuevaCantidad; // se actualiza el dato en memoria
          carrito = [...carrito];
          localStorage.setItem('carrito', JSON.stringify(carrito));
    
          // Se calcula el nuevo subtotal
          const fila = e.target.closest("tr");
          const celdaSubtotal = fila.querySelector(".subtotal");
          celdaSubtotal.textContent = (producto.precioVenta * nuevaCantidad).toFixed(2);
        });
      });
    }

    function eliminarProducto(){
      document.querySelectorAll('.bi').forEach(bt => {
        bt.addEventListener('click', function() {
          let detalleProducto = this.closest('tr');
          let idProducto = detalleProducto.getAttribute('data-id');
          console.log(idProducto);
          carrito = carrito.filter(producto => producto.idProducto !== parseInt(idProducto));
          detalleProducto.remove();
          localStorage.setItem('carrito', JSON.stringify(carrito));
          if(carrito.length === 0 || !carrito ){
            document.getElementById('confirmar-compra').disabled = true;
          }
        })
      })
    }

    function deshabilitarBoton(){
      if(carrito.length === 0 || !carrito ){
        document.getElementById('confirmar-compra').disabled = true;
      }
  }
  
})

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