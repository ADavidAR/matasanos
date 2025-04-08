document.addEventListener("DOMContentLoaded", function () {
  // Variables del carrito
  let carrito = JSON.parse(localStorage.getItem('carrito')) || [];
  let seleccionado = -1;
  mostrarProductosCarrito(carrito);
  calcularTotal();

  function mostrarClienteSeleccionado(nombreCliente, rtnCliente) {
    document.getElementById("cliente").textContent = nombreCliente;
    document.getElementById("rtn").textContent = rtnCliente;
  }

  function filtrarSugerencias(searchText) {
    suggestionsList.innerHTML = '';
    const resultados = clientes.filter(cliente =>
      cliente.nombre.toLowerCase().includes(searchText.toLowerCase())
    );

    
    
    resultados.forEach(resultado => {
      const option = document.createElement('option');
      option.value = resultado.nombre;
      suggestionsList.appendChild(option);
    });
  }

  // Event listeners
  searchInput.addEventListener('input', (e) => {
    if (e.target.value.length > 0) {
      filtrarSugerencias(e.target.value);
    } else {
      suggestionsList.innerHTML = '';
    }
  });

  searchInput.addEventListener('change', (e) => {
    const clienteSeleccionado = clientes.find(
      cliente => cliente.nombre === e.target.value
    );
    
    seleccionado = 1;
    console.log(seleccionado);

    if (clienteSeleccionado) {
      mostrarClienteSeleccionado(clienteSeleccionado.nombre, clienteSeleccionado.rtn);
    }
  });

  // Funciones del carrito (corregidas)
  function mostrarProductosCarrito(listaCarrito) {
    const carritoBody = document.getElementById('productos-body');
    carritoBody.innerHTML = ''; // Limpiar contenido antes de agregar

    listaCarrito.forEach(producto => {
      const contenido = `              
        <tr>
          <td>${producto.nombreProducto}</td>
          <td>${producto.cantidad}</td>
          <td>${producto.precioVenta.toFixed(2)}</td>
          <td class="impuesto">${(producto.impuesto*100)}%</td>
          <td class="total">${((producto.precioVenta + producto.precioVenta *producto.impuesto)*producto.cantidad).toFixed(2)}</td>

        </tr>`;
      carritoBody.innerHTML += contenido;
    });
  }

  function calcularTotal() {
    let subtotales = document.querySelectorAll('.total');
    let subtotal = 0;
    
    subtotales.forEach(s => {
      subtotal += parseFloat(s.textContent); 
    });
    document.getElementById('total-total').textContent = subtotal.toFixed(2);
  }

  let guardarFactura = document.querySelector('.btn-guardar');
  guardarFactura.addEventListener("click", function(){
    localStorage.removeItem('carrito');
    window.location.href = '/ventas';
  })

});