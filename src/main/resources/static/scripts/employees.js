//cargar las cosas al entrar a la pagina
let userData
let permisoCrear=false;
window.addEventListener("DOMContentLoaded", async () => {
    const roleH5 = document.querySelector("#title");
    const optionsNav = document.querySelector("#nav-options");
    
     userData = JSON.parse(localStorage.getItem("userData"));

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
        if(p.permiso.descripcion == "Empleados" && p.creacion==true){
          permisoCrear=true
        }
    })
   
    
      
      if(permisoCrear){
        const guardar =document.querySelector("#submitBtn")
        guardar.disabled=false
      guardar.addEventListener("click",guardarEmpleado)
    }
     
     
    
    
    cargarCiudades()
    cargarCargos()
    cargarSucursal()
    cargarEmpleados()

})



async function cargarCiudades() {
const ciudades = document.querySelector("#city")
    try {
      let response = await fetch("/api/empleados/cargarCiudades");
      let data = await response.json();
   
      data.forEach((d) => {
        const newOPtion = document.createElement("option");
        newOPtion.setAttribute("value", d["idCiudad"]);
        newOPtion.innerHTML = d["ciudad"];
        ciudades.appendChild(newOPtion);
      });
    } catch (error) {
      console.error("Error al obtener ciudades:", error);
    }
  }
  async function cargarColonias() {

    const idCiudad= document.querySelector("#city").value
    console.log(idCiudad)

    const ciudades = document.querySelector("#colonia")
    ciudades.innerHTML=`<option value="0">Seleccione una colonia</option>`
    ciudades.disabled=false
        try {
          let response = await fetch(`/api/empleados/cargarColonias/${idCiudad}`);
          let data = await response.json();
       
          data.forEach((d) => {
            const newOPtion = document.createElement("option");
            newOPtion.setAttribute("value", d["idColonia"]);
            newOPtion.innerHTML = d["nombreColonia"];
            ciudades.appendChild(newOPtion);
          });
        } catch (error) {
          console.error("Error al obtener colonias:", error);
        }
      }
      async function cargarCargos() {
        const ciudades = document.querySelector("#position")
            try {
              let response = await fetch("/api/empleados/cargarCargos");
              let data = await response.json();
           
              data.forEach((d) => {
                const newOPtion = document.createElement("option");
                newOPtion.setAttribute("value", d["idCargo"]);
                newOPtion.innerHTML = d["descripcion"];
                ciudades.appendChild(newOPtion);
              });
            } catch (error) {
              console.error("Error al obtener cargos:", error);
            }
          }
          async function cargarSucursal() {
            const ciudades = document.querySelector("#branch")
                try {
                  let response = await fetch("/api/empleados/cargarSucursales");
                  let data = await response.json();
               
                  data.forEach((d) => {
                    const newOPtion = document.createElement("option");
                    newOPtion.setAttribute("value", d["idSucursal"]);
                    newOPtion.innerHTML = d["nombreSucursal"];
                    ciudades.appendChild(newOPtion);
                  });
                } catch (error) {
                  console.error("Error al obtener sucursales:", error);
                }
              }
              
              function convertirDatosAJason() {
                //convertir los datos de formulario en un json
                const datosForm= document.querySelectorAll(".form-control,.form-select")
                const JSON={
                  dni:datosForm[0].value,
                  primerNombre:datosForm[1].value,
                  segundoNombre:datosForm[2].value,
                  
                  primerApellido:datosForm[3].value,
                  segundoApellido:datosForm[4].value,
                  idCargo:parseInt(datosForm[5].value),
                  idSucursal:parseInt(datosForm[6].value),
                  salario:parseFloat(datosForm[7].value),
                  fechaContratacion:datosForm[8].value,
                  telefono:datosForm[9].value,
                  correo:datosForm[10].value,
                  idCiudad:parseInt(datosForm[11].value),
                  idColonia:parseInt(datosForm[12].value),
                  referencia:datosForm[13].value,
                  idUsuario:userData.idUsuario

                }
                

                console.log(JSON)
                return JSON
              }
              
              
              async function guardarEmpleado() {
          
                try {
                  let response = await fetch(`/api/empleados/crearEmpleado`, {
                    method: "POST",
                headers: {
                  "Content-Type": "application/json",
                },
                body: JSON.stringify(convertirDatosAJason())
              });
              if (response.ok) {
                alert("Empleado Creado Correctamente");
                
                location.reload();
              } else {
                alert("Error al guardar al empleado");
            }
          
            
          } catch (error) {
            console.error("Error en la solicitud");
            }
          }
          async function cargarDatosPersona(){
            if(document.querySelector("#idNumber").value.length==13){
            try {
              let response = await fetch(`/api/empleados/cargarDatosPersona/${document.querySelector("#idNumber").value}`);
              let data = await response.json();
               
              if (response.ok) {
                const inputPersona = document.querySelectorAll('.personData input:not([type="date"]) , .contactDetails input:not([type="date"]), .contactDetails .form-select, .contactDetails textarea')
                inputPersona[1].value=data[0].primerNombre
                inputPersona[2].value=data[0].segundoNombre
                inputPersona[3].value=data[0].primerApellido
                inputPersona[4].value=data[0].segundoApellido
                inputPersona[5].value=data[2].telefono
                inputPersona[6].value=data[1].correo
                
                inputPersona[7].value=data[0].direccion.colonia.ciudad.idCiudad
               
                if(data[0].direccion.colonia.ciudad.idCiudad>0) {await cargarColonias()}
                
              
                inputPersona[9].value=data[0].direccion.referencia
                inputPersona[8].value=data[0].direccion.colonia.idColonia
              } else {
                console.log("Error al cargar los datos");}
              } catch (error) {
        console.error("no se encontraron datos");}
        }
          }

          async function cargarDatosEmpleado(idEmpleado){
            
            try {
              let response = await fetch(`/api/empleados/cargarDatosEmpleado/${idEmpleado}`);
              let data = await response.json();
              console.log(data)
              if(response.ok) {
                const inputPersona = document.querySelectorAll('.employmentData input, .employmentData select, #idNumber')
                inputPersona[0].value=data.persona.dni
                await cargarDatosPersona()
                inputPersona[1].value=data.cargo.idCargo
                inputPersona[2].value=data.sucursal.idSucursal
                inputPersona[3].value=data.salario
                inputPersona[4].value=data.fechaContratacion
                const inputs=document.querySelectorAll("#employeeForm input, #employeeForm select, #employeeForm textarea, #employeeForm button")
                inputs.forEach(i=>{console.log(i.disabled=true)})
                const editar=document.querySelector("#update")
                const eliminar=document.querySelector("#delete")
                const modal=document.querySelector("#openDelete")
               let permisoModificar=false;
                userData.rol.permisos.forEach(p=>{
                  if(p.permiso.descripcion == "Empleados" && p.eliminacion==true){permisoModificar=true}
                 
                })
                if(permisoModificar){
                editar.disabled=false
                modal.disabled=false
                editar.dataset.id=idEmpleado
                eliminar.dataset.id=idEmpleado
                editar.addEventListener("click",function(){
                  let idEmpleado = this.dataset.id
                  
                  inputs.forEach(i=>{console.log(i.disabled=false)})
                  document.querySelector("#submitBtn").removeEventListener("click",guardarEmpleado);

                  document.querySelector("#submitBtn").addEventListener("click",function(){ModificardEmpleado(idEmpleado)})

                })
                eliminar.addEventListener("click",function(){
                  let idEmpleado = this.dataset.id
                  eliminarEmpleado(idEmpleado)
                }
                  )
              }
             
                
              } else {
                console.log("Error al cargar los datos");}
              } catch (error) {
        console.error("error al cargar");}
        }


         async function ModificardEmpleado(idEmpleado){
            
          try {
            let response = await fetch(`/api/empleados/modificarEmpleado/${idEmpleado}`, {
              method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(convertirDatosAJason())
        });
        if (response.ok) {
          await alert("Empleado actualizado Correctamente");
          
          location.reload();
        } else {
          alert("Error al actualizar al empleado");
      }
    
      
    } catch (error) {
      console.error("Error en la solicitud");
      }
        }


        async function eliminarEmpleado(idEmpleado){
            
          try {
            let response = await fetch(`/api/empleados/eliminarEmpleado/${idEmpleado}`,{
              method: "POST",
          headers: {
            "Content-Type": "application/json",
          }});
        if (response.ok) {
          await alert("Empleado eliminado Correctamente");
          
          location.reload();
        } else {
          alert("Error al eliminar  al empleado");
      }
    
      
    } catch (error) {
      console.error("Error en la solicitud");
      }
        }


          async function cargarEmpleados() {
            const tabla = document.querySelector("#employeesTable tbody");
            try {
              let response = await fetch( `/api/empleados/cargarEmpleados`);
              data = await response.json();
              data.forEach(d=>{
                const nuevaFila = document.createElement("tr")
                const dni= document.createElement("td")
                const nombre= document.createElement("td")
                const cargo= document.createElement("td")
                const acccion = document.createElement("td")
                const verDatos=document.createElement(`button`)
                const diseño = document.createElement("i")
                diseño.classList.add("fas", "fa-eye")
                verDatos.dataset.id = d.idEmpleado
                verDatos.addEventListener("click",function(){
                  idEmpleado= this.dataset.id
                  cargarDatosEmpleado(idEmpleado)
                })
                verDatos.appendChild(diseño)
                verDatos.classList.add("btn", "btn-sm", "btn-outline-primary", "me-1")

                dni.innerHTML=d.persona.dni
                nuevaFila.appendChild(dni)
                nombre.innerHTML=d.persona.primerNombre  +" "+(d.persona.segundoNombre ?? "" )+" "+d.persona.primerApellido  +" "+(d.persona.segundoApellido ?? "" )
                nuevaFila.appendChild(nombre)            
                cargo.innerHTML=d.cargo.descripcion
                nuevaFila.appendChild(cargo)        
                acccion.appendChild(verDatos)
                nuevaFila.appendChild(acccion)   
                
                tabla.appendChild(nuevaFila)

            })
             
            } catch (error) {
              console.error("Error al obtener los empleados:", error);
            }
          }

          function limpiar(){

          }


          
         
              
          document.querySelector("#resetBtn").addEventListener("click",limpiar)
          document.querySelector("#city").addEventListener("change",cargarColonias)
         
          document.querySelector("#idNumber").addEventListener("input", cargarDatosPersona)