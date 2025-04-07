package com.matasanos.controller;

import com.matasanos.model.Receta;
import com.matasanos.service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
    RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping("")
    public List<Receta> listarRecetas() {
        return recetaService.listarRecetas();
    }

    @GetMapping("/{idReceta}")
    public ResponseEntity<?> obtenerRecetaPorId(@PathVariable int idReceta) {
        Receta r = recetaService.obtenerRecetaPorId(idReceta);

        return  (r != null) ? ResponseEntity.ok(r) : ResponseEntity.ok(Collections.emptyMap());
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarReceta(@RequestBody Receta receta) {
        recetaService.agregar(receta);
        return ResponseEntity.ok("");
    }

    @PostMapping("/modificar")
    public ResponseEntity<?> modificarReceta(@RequestBody Receta receta) {
        recetaService.modificar(receta);
        return ResponseEntity.ok("");
    }

    @GetMapping("/verificar/eliminar/{idReceta}")
    public ResponseEntity<?> verificarEliminacion(@PathVariable int idReceta) {
        Map<String, Object> response = new HashMap<>();

        if(!recetaService.existe(idReceta))      {
            response.put("msg", "receta inexistente");

            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("eliminar/{idReceta}")
    public void eliminarReceta(@PathVariable int idReceta) {
        recetaService.eliminar(idReceta);
    }
}
