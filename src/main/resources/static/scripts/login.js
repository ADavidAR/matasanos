

(function () {
    
    document.addEventListener("DOMContentLoaded", (e) => {
        const form = document.querySelector("#main-container ");
        setTimeout(() => {
            form.style.opacity = 1;
            form.style.top = "0px"
        }, 500)
        
    })
    let form = document.querySelector('#login-form');
    const msgPass = document.querySelector("#msg-pass");
    const msgUser = document.querySelector("#msg-user");

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
            document.querySelector("#password").classList.remove("is-invalid");
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
    const msgPass = document.querySelector("#msg-pass");
    const msgUser = document.querySelector("#msg-user");
    let user = document.querySelector("#user");
    let pass = document.querySelector("#password");

    let data = {
        user: user.value,
        pass: pass.value
    } 

    let userData = await fetch("/api/usuarios/verificar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then( response => response.json())

    if(Object.keys(userData).length === 0 || userData.status !== undefined) {
        
        msgUser.textContent = "";
        msgPass.textContent = "Usuario o contraseña incorrecto";

        pass.classList.add("is-invalid");
        return;

    }

    localStorage.setItem("userData", JSON.stringify(userData));

    window.location.href = "/home"
}

function mostrarContraseña() {
    let passwordInput = document.getElementById("password");
    let toggleIcon = document.getElementById("toggleIcon");

    if (passwordInput.type === "password") {
        passwordInput.type = "text";
        toggleIcon.classList.remove("bi-eye");
        toggleIcon.classList.add("bi-eye-slash");
    } else {
        passwordInput.type = "password";
        toggleIcon.classList.remove("bi-eye-slash");
        toggleIcon.classList.add("bi-eye");
    }
}