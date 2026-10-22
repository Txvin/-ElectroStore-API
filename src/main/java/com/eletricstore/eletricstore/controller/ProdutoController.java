package com.eletricstore.eletricstore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletricstore.eletricstore.dto.ProdutoDTO;
import com.eletricstore.eletricstore.service.ProdutoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping ("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List <ProdutoDTO> listar(@RequestParam(required = false) Long categoriaId) {
        return service.listar(categoriaId);
    }

    @GetMapping("/{id}")
    public ProdutoDTO buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@Valid @RequestBody ProdutoDTO dto) {
        ProdutoDTO criado = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizar (@PathVariable Long id,@Valid @RequestBody ProdutoDTO dto) {
        return service.atualizar(id, dto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deletar (@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    
    

    
}
