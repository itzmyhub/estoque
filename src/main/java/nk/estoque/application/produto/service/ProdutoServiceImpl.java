package nk.estoque.application.produto.service;

import nk.estoque.domain.repositories.ProdutoRepository;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.infraestructure.utils.exceptions.IdNaoEncontradoException;
import nk.estoque.infraestructure.web.produto.ProdutoFilter;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.infraestructure.persistence.produto.ProdutoPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Page<Produto> listaPaginada(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Page<Produto> listaPaginada(Pageable pageable, ProdutoFilter filter) {
        if (filter.getNome().isEmpty() || filter.getNome().get().isBlank()) {
            return produtoRepository.findAll(pageable);
        }
        return produtoRepository.findByNomeContainingIgnoreCase(filter.getNome().get(), pageable);
    }

    @Override
    public List<Produto> produtosPorId(List<Long> ids) {
        return produtoRepository.findAllById(ids);
    }

    @Override
    public Produto produtoPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Produto com ID " + id + " não encontrado"));
    }

    @Override
    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Long id, Produto produto) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Produto com ID " + id + " não encontrado"));

        existente.atualizar(produto);
        return produtoRepository.save(existente);
    }

    @Override
    public void atualizarProdutos(List<Produto> produtos) {
        produtos.forEach(produtoRepository::save);
    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
