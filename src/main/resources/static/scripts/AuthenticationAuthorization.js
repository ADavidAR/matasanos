class AuthenticationAuthorization {
    static checkAuthorization () {
        return (localStorage.getItem("userData") !== null)
    }
    static checkAuthorization(endpointUrl) {
        let userData = JSON.parse(localStorage.getItem("userData"));
        let auth = {
            access: false,
            mod: false,
            create: false,
            del: false
        };
        for(const p of userData.rol.permisos) {
            if(p.permiso.endpointUrl === endpointUrl) {
                auth.access = p.acceso;
                auth.mod = p.modificacion;
                auth.create = p.creacion;
                auth.del = p.eliminacion;
                break;
            }
        }

        return auth;
    }
}

let userData = JSON.parse(localStorage.getItem("userData"));