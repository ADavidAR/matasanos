package com.matasanos.service;

import com.matasanos.model.Departamento;
import com.matasanos.repo.DepartamentoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepo departamentoRepo;

    public DepartamentoService(DepartamentoRepo departamentoRepo) {
        this.departamentoRepo = departamentoRepo;
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoRepo.listarDepartamentos();
    }

    public void guardarNuevoDepartamento(Departamento departamento) {
        departamentoRepo.guardarNuevoDepartamento(departamento);
    }
}