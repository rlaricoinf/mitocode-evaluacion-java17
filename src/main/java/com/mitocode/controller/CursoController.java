package com.mitocode.controller;

import com.mitocode.dto.CursoDTO;
import com.mitocode.exception.NewModelNotFoundException;
import com.mitocode.model.Curso;
import com.mitocode.service.ICursoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final ICursoService service;

    @Qualifier("cursoMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> readAll()throws Exception{
        List<CursoDTO> lst = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Curso obj = service.readById(id);
        if(obj==null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> create(@Valid @RequestBody CursoDTO dto) throws Exception{
        Curso obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CursoDTO> update(@Valid @RequestBody CursoDTO dto) throws Exception{
        Curso obj = service.readById(dto.getId());

        if(obj == null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }

        Curso cat = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(cat), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Curso obj = service.readById(id);

        if(obj == null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private CursoDTO convertToDto(Curso obj) {
        return mapper.map(obj, CursoDTO.class);
    }

    private Curso convertToEntity(CursoDTO dto){
        return mapper.map(dto, Curso.class);
    }



}
