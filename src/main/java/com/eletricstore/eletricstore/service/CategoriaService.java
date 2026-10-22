package com.eletricstore.eletricstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eletricstore.eletricstore.dto.CategoriaDTO;
import com.eletricstore.eletricstore.exception.RecursoNaoEncontradoException;
import com.eletricstore.eletricstore.exception.RegraNegocioException;
import com.eletricstore.eletricstore.model.Categoria;
import com.eletricstore.eletricstore.repository.CategoriaRepository;
import com.eletricstore.eletricstore.repository.ProdutoRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    private CategoriaDTO toDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome());
    }

    private Categoria buscarEntidade(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada: " + id));
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> listar() {
        return categoriaRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO buscarPorId(Long id) {
        return toDTO(buscarEntidade(id));
    }

    @Transactional
    public CategoriaDTO criar(CategoriaDTO dto) {
        String nome = dto.nome().trim();

        if (categoriaRepository.existsByNomeIgnoreCase(nome)) {
            throw new RegraNegocioException("Já existe uma categoria com o nome: " + nome);
        }

        Categoria categoria = new Categoria();
        categoria.setNome(nome);

        Categoria salva = categoriaRepository.save(categoria);
        return toDTO(salva);
    }

    @Transactional
    public CategoriaDTO atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = buscarEntidade(id);
        String nome = dto.nome().trim();

        if (!categoria.getNome().equalsIgnoreCase(nome)
                && categoriaRepository.existsByNomeIgnoreCase(nome)) {
            throw new RegraNegocioException("Já existe uma categoria com o nome: " + nome);
        }
        categoria.setNome(nome);
        return toDTO(categoriaRepository.save(categoria));
    }

    @Transactional
    public void deletar(Long id) {
        Categoria categoria = buscarEntidade(id);

        if (produtoRepository.existsByCategoriaId(id)) {
            throw new RegraNegocioException("Não é possível excluir uma categoria que possui produtos");
        }

        categoriaRepository.delete(categoria);
    }
}
