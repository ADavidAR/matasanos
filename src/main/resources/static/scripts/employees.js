//cargar las cosas al entrar a la pagina
let userData
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
    })
    cargarCiudades()
    cargarCargos()
    cargarSucursal()

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

    const ciudades = document.querySelector("#colonia")
    ciudades.innerHTML=`<option value="">Seleccione una ciudad</option>`
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
                  fechaNacimiento:datosForm[5].value,
                  idCargo:parseInt(datosForm[6].value),
                  idSucursal:parseInt(datosForm[7].value),
                  salario:parseFloat(datosForm[8].value),
                  fechaContratacion:datosForm[9].value,
                  telefono:datosForm[10].value,
                  correo:datosForm[11].value,
                  idCiudad:parseInt(datosForm[12].value),
                  idColonia:parseInt(datosForm[13].value),
                  referencia:datosForm[14].value,
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
                alert("Solicitud creada correctamente");
                
                location.reload();
              } else {
                alert("Error al crear la solicitud");
            }
          
            
          } catch (error) {
            console.error("Error en la solicitud");
            }
          }
          async function cargarDatosPersona(){
            try {
              let response = await fetch(`/api/empleados/cargarDatosPersona`);
              let data = await response.json();
              console.log(data)
             /* const inputPersona = document.querySelectorAll('.personData input:not([type="date"]) , .contactDetails input:not([type="date"]), .contactDetails .form-select, .contactDetails textarea')
              inputPersona[1].value=data.primerNombre
              inputPersona[2].value=data.segundoNombre
              inputPersona[3].value=data.primerApellido
              inputPersona[4].value=data.segundoApellido
              inputPersona[5].value=data.telefono
              inputPersona[6].value=data.correo
              inputPersona[6].value=data.idCiudad
              inputPersona[7].value=data.idColonia
              inputPersona[8].value=data.referencia*/
      } catch (error) {
        console.error("no se encontraron datos");
        }
          }
          
         
          
          document.querySelector("#city").addEventListener("change",cargarColonias)
          document.querySelector("#submitBtn").addEventListener("click",guardarEmpleado)
          document.querySelector("#idNumber").addEventListener("input", cargarDatosPersona)