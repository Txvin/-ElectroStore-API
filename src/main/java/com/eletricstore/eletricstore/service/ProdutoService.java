package com.eletricstore.eletricstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eletricstore.eletricstore.dto.ProdutoDTO;
import com.eletricstore.eletricstore.exception.RecursoNaoEncontradoException;
import com.eletricstore.eletricstore.model.Categoria;
import com.eletricstore.eletricstore.model.Produto;
import com.eletricstore.eletricstore.repository.CategoriaRepository;
import com.eletricstore.eletricstore.repository.ProdutoRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }


    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getCategoria().getId(),
                produto.getCategoria().getNome());
    }

    private Produto buscarEntidade(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto não encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> listar(Long categoriaId) {
        List<Produto> produtos;
        if (categoriaId == null) {
            produtos = produtoRepository.findAll();
        } else {
            produtos = produtoRepository.findByCategoriaId(categoriaId);
        }

        return produtos.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO buscarPorId(Long id) {
        return toDTO(buscarEntidade(id));
    }

    @Transactional
    public ProdutoDTO criar(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Categoria não encontrada: " + dto.categoriaId()));

        Produto produto = new Produto();
        produto.setNome(dto.nome().trim());
        produto.setPreco(dto.preco());
        produto.setCategoria(categoria);

        return toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produto = buscarEntidade(id);

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Categoria não encontrada: " + dto.categoriaId()));

        produto.setNome(dto.nome().trim());
        produto.setPreco(dto.preco());
        produto.setCategoria(categoria);

        return toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public void deletar(Long id) {
        Produto produto = buscarEntidade(id);
        produtoRepository.delete(produto);
    }
}