//selecionar las filas de la tabla en el modal
      // Selecciona todas las filas de la tabla
      const rows = document.querySelectorAll('#myTable tbody tr');

      // Recorre cada fila y añade un evento de clic
      rows.forEach(row => {
        row.addEventListener('click', function() {
          // Elimina la clase "seleccionado" de todas las filas
          rows.forEach(r => r.classList.remove('table-active'));
          
          // Añade la clase "seleccionado" a la fila clicada
          this.classList.add('table-active');
        });
      });


//pasar los datos selecionados a la otra tabla
 // Selecciona las filas de la tabla original
 
 let selectedRow = null;

 // Recorre las filas y añade un evento de clic para seleccionarlas
 rows.forEach(row => {
   row.addEventListener('click', function() {
     // Elimina la clase "seleccionado" de todas las filas
     rows.forEach(r => r.classList.remove('table-active'));

     // Añade la clase "seleccionado" a la fila clicada
     this.classList.add('table-active');

     // Guarda la fila seleccionada
     selectedRow = this;
   });
 });

 // Función para enviar los datos seleccionados a la tabla de destino
 document.getElementById('sendDataBtn').addEventListener('click', function() {
   if (selectedRow) {
     // Obtén los datos de la fila seleccionada
     const cells = selectedRow.querySelectorAll('td');
     const data = [
       cells[0].innerText, // Número
       cells[1].innerText, // Nombre
       
     ];

     // Crea una nueva fila en la tabla de destino
     const newRow = document.createElement('tr');
     data.forEach(text => {
       const newCell = document.createElement('td');
       newCell.innerText = text;
       newRow.appendChild(newCell);
     });

     // Añade la nueva fila a la tabla de destino
     document.querySelector('#table2 tbody').appendChild(newRow);
   } else {
     alert('Por favor, selecciona una fila primero.');
   }
 });
