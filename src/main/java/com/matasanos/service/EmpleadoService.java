package com.matasanos.service;

import com.matasanos.model.Empleado;
import com.matasanos.repo.EmpleadoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    EmpleadoRepo empleadoRepo;

    public EmpleadoService(EmpleadoRepo empleadoRepo) {
        this.empleadoRepo = empleadoRepo;
    }

    public List<Empleado> listarEmpleadosSinUsuario() {
        return empleadoRepo.listarEmpleadosSinUsuario();
    }
}
