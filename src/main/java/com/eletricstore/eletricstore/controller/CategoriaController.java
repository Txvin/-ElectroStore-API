package com.eletricstore.eletricstore.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletricstore.eletricstore.dto.CategoriaDTO;
import com.eletricstore.eletricstore.service.CategoriaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoriaDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public CategoriaDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> criar (@Valid @RequestBody CategoriaDTO dto) {
        CategoriaDTO criada = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping ("/{id}")
    public CategoriaDTO atualizar (@PathVariable Long id, @Valid @RequestBody CategoriaDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletar (@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}