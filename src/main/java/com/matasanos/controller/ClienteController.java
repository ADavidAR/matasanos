package com.matasanos.controller;

import com.matasanos.model.Cliente;
import com.matasanos.model.Persona;
import com.matasanos.service.ClienteService;
import com.matasanos.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;
    private PersonaService personaService;

    public ClienteController(ClienteService clienteService, PersonaService personaService) {
        this.clienteService = clienteService;
        this.personaService = personaService;
    }

    @GetMapping("")
    public List<Cliente> listarSucursales() {
        return clienteService.listarClientes();
    }

    @PostMapping("/verificar/edicion")
    public ResponseEntity<?> validarModificacion(@RequestBody Cliente cliente) {

        Map<String, Object> response = new HashMap<>();
        if(clienteService.verificarModificacionDni(cliente.getPersona().getDni(), cliente.getPersona().getIdPersona())) {
            response.put("msg", "dni existente");
            return ResponseEntity.badRequest().body(response);
        }

        if(clienteService.verificarModificacionRtn(cliente.getRtn(), cliente.getIdCliente())) {
            response.put("msg", "rtn existente");
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verificar/crear")
    public ResponseEntity<?> validarCreacion(@RequestBody Cliente  cliente) {

        Map<String, Object> response = new HashMap<>();

        if(clienteService.verificarCreacionRtn(cliente.getRtn())) {
            response.put("msg", "rtn existente");
            return ResponseEntity.badRequest().body(response);
        }

        if(clienteService.verificarCreacionDni(cliente.getPersona().getDni())) {
            response.put("msg", "dni existente");
            return ResponseEntity.badRequest().body(response);
        }

        Persona persona = personaService.obtenerPersonaPorDni(cliente.getPersona().getDni());
        if(persona != null) {
            response.put("msg", "persona existente");
            response.put("fullName", String.format("%s %s %s %s",
                    persona.getPrimerNombre(),
                    Optional.ofNullable(persona.getSegundoNombre()).orElse(""),
                    persona.getPrimerApellido(),
                    Optional.ofNullable(persona.getSegundoApellido()).orElse("")
            ));
            response.put("id", persona.getIdPersona());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<?> obtenerSucursalDireccion(@PathVariable int idCliente) {
        Cliente c = clienteService.obtenerCliente(idCliente);

        return ( c != null ) ? ResponseEntity.ok(c) : ResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/verificar/eliminar/{idCliente}")
    public ResponseEntity<?> verificarEliminar(@PathVariable int idCliente) {
        Map<String, Object> response = new HashMap<>();

        if(clienteService.enUso(idCliente)) {
            response.put("msg", "cliente en uso");
            return ResponseEntity.badRequest().body(response);
        }

        if(!clienteService.existe(idCliente)) {
            response.put("msg", "cliente inexistente");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{idCliente}")
    public ResponseEntity<?> eliminar(@PathVariable int idCliente) {
        clienteService.eliminarCliente(idCliente);
        return ResponseEntity.ok(Collections.emptyMap());
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Cliente cliente) {
        clienteService.modificarCliente(cliente);
    }

    @PostMapping("/agregar")
    public void agregarConNuevaPersona(@RequestBody Cliente cliente) {
        clienteService.agregarClienteConNuevaPersona(cliente);
    }

    @PostMapping("/agregar/mod")
    public void agregarModificandoPersonaExistente(@RequestBody Cliente cliente) {
        clienteService.agregarClienteModificandoPersonaExistente(cliente);
    }

    @PostMapping("/agregar/existente")
    public void agregarConPersonaExistente(@RequestBody Cliente cliente) {
        clienteService.agregarClienteConPersonaExistente(cliente);
    }

}
