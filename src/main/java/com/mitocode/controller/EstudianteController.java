package com.mitocode.controller;

import com.mitocode.model.Estudiante;
import com.mitocode.service.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final IEstudianteService service;

    @GetMapping
    public ResponseEntity<List<Estudiante>> readAll()throws Exception{
        List<Estudiante> lst = service.readAll();
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> readById(@PathVariable("id") Integer id) throws Exception{
        Estudiante obj = service.readById(id);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Estudiante> create(@RequestBody Estudiante estudiante) throws Exception{
        Estudiante obj = service.save(estudiante);
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }


}
