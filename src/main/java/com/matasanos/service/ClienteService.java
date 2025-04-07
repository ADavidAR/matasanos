package com.matasanos.service;

import com.matasanos.model.Cliente;
import com.matasanos.repo.ClienteRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepo clienteRepo;

    public ClienteService(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }
    public List<Cliente> listarClientes() {
        return clienteRepo.listarClientes();
    }

    public boolean verificarModificacionDni(String dni, int idPersona) {
        Cliente c = clienteRepo.obtenerClientePorDni(dni);
        return (c != null && c.getPersona().getIdPersona() != idPersona);
    }

    public boolean verificarModificacionRtn(String rtn, int idCliente) {
        Cliente c = clienteRepo.obtenerClientePorRtn(rtn);
        return (c != null && c.getIdCliente()   != idCliente);
    }

    public boolean verificarCreacionDni(String dni) {
        Cliente c = clienteRepo.obtenerClientePorDni(dni);
        return c != null;
    }

    public boolean verificarCreacionRtn(String rtn) {
        Cliente c = clienteRepo.obtenerClientePorRtn(rtn);
        return c != null;
    }

    public Cliente obtenerCliente(int idCliente) {
        return clienteRepo.obtenerClientePorId(idCliente);
    }

    public boolean enUso(int idCliente) {
        return clienteRepo.enUso(idCliente);
    }

    public boolean existe(int idCliente) {
        return clienteRepo.existe(idCliente);
    }

    public void eliminarCliente(int idCliente) {
        clienteRepo.eliminar(idCliente);
    }

    public void modificarCliente(Cliente cliente) {
            clienteRepo.modificar(cliente);
    }

    public void agregarClienteModificandoPersonaExistente(Cliente cliente) {
        clienteRepo.agregarCLienteModificandoPersonaExistente(cliente);
    }

    public void agregarClienteConPersonaExistente(Cliente cliente) {
        clienteRepo.agregarClienteConPersonaExistente(cliente);
    }

    public void agregarClienteConNuevaPersona(Cliente cliente) {
        clienteRepo.agregarClienteConNuevaPersona(cliente);
    }
}
