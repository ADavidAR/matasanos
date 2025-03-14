

(function () {

    let form = document.querySelector('#login-form');
    const msgPass = document.querySelector("#msg-pass")
    const msgUser = document.querySelector("#msg-user")

    const inputs = document.querySelectorAll(".form-control");

    form.addEventListener('submit', async function (event) {
        
        event.preventDefault();

        if (!form.checkValidity()) {
            
            event.stopPropagation();
            msgUser.textContent = "Ingrese el usuario";
            msgPass.textContent = "Ingrese el contraseña";
        }

        form.classList.add('was-validated')
        
        
        for(input of inputs) {

            if (input.value.trim() === '') return;
        }

        await validarUsuario();


    }, false)

    inputs.forEach(input => {
        input.addEventListener("input", (event) => {
            if(input.validity.valid) {
                msgUser.textContent = "Ingrese el usuario";
                msgPass.textContent = "Ingrese el contraseña";
                input.classList.remove("is-invalid");
            } else {
                input.classList.add("is-invalid");
            }
        })
    })

}) ()

async function validarUsuario() {
    const msgPass = document.querySelector("#msg-pass")
    const msgUser = document.querySelector("#msg-user")
    let usuario = document.querySelector("#user");
    let pass = document.querySelector("#password");

    let data = {
        usuario: usuario.value,
        pass: pass.value
    } 

    console.log(data)

    let datosUsuario = await fetch("/api/usuarios/verificar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then( response => response.json())

    if(Object.keys(datosUsuario).length === 0) {
        
        msgUser.textContent = "";
        msgPass.textContent = "Usuario o contraseña incorrecto";

        pass.classList.add("is-invalid");
        console.log(`bAD reUQEST ${datosUsuario}`)
        return;

    }

    localStorage.setItem("datos", JSON.stringify(datosUsuario));

    window.location.href = "/temp"
}