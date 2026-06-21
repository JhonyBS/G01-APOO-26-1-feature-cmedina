package com.example.tutoria03.controllers;

import java.util.ArrayList;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tutoria03.models.Docente;
import com.example.tutoria03.services.DocenteService;

@RestController
@RequestMapping("/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping
    public ResponseEntity<ArrayList<Docente>> getAll() {
        return ResponseEntity.ok(docenteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById(@PathVariable int id) {
        return ResponseEntity.ok(docenteService.getDocenteById(id));
    }

    @PostMapping
    public ResponseEntity<Docente> save(@RequestBody Docente docente) {
        return ResponseEntity.ok(docenteService.save(docente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> update(
            @RequestBody Docente docente,
            @PathVariable int id) {

        return ResponseEntity.ok(docenteService.update(docente, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        docenteService.delete(id);
    }
}