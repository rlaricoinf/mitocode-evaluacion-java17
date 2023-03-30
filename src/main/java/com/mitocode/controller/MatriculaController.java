package com.mitocode.controller;

import com.mitocode.dto.MatriculaDTO;
import com.mitocode.exception.NewModelNotFoundException;
import com.mitocode.model.Matricula;
import com.mitocode.service.IMatriculaService;
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
@RequestMapping("/matriculas")
@RequiredArgsConstructor
public class MatriculaController {

    private final IMatriculaService service;

    @Qualifier("matriculaMapper")
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MatriculaDTO>> readAll()throws Exception{
        List<MatriculaDTO> lst = service.readAll().stream().map(this::convertToDto).collect(Collectors.toList());
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatriculaDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Matricula obj = service.readById(id);
        if(obj==null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(this.convertToDto(obj), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MatriculaDTO> create(@Valid @RequestBody MatriculaDTO dto) throws Exception{
        Matricula obj = service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MatriculaDTO> update(@Valid @RequestBody MatriculaDTO dto) throws Exception{
        Matricula obj = service.readById(dto.getId());

        if(obj == null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }

        Matricula cat = service.update(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(cat), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Matricula obj = service.readById(id);

        if(obj == null){
            throw new NewModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    private MatriculaDTO convertToDto(Matricula obj) {
        return mapper.map(obj, MatriculaDTO.class);
    }

    private Matricula convertToEntity(MatriculaDTO dto){
        return mapper.map(dto, Matricula.class);
    }



}
