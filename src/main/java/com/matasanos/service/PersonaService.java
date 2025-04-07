package com.matasanos.service;

import com.matasanos.model.Persona;
import com.matasanos.repo.PersonaRepo;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
    private final PersonaRepo personaRepo;

    public PersonaService(PersonaRepo clienteRepo) {
        this.personaRepo = clienteRepo;
    }

    public Persona obtenerPersonaPorDni(String dni) {
        Persona p = personaRepo.obtenerPersonaPorDni(dni);
        return p;
    }
}
