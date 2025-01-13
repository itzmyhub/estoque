package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import nk.estoque.application.infraestructure.web.produto.ProdutoFilter;
import nk.estoque.domain.model.produto.TodosProdutos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class TodosProdutosAdapter implements TodosProdutos {
    private final ProdutoRepository produtoRepository;

    public TodosProdutosAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Page<Produto> listaPaginada(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public Page<Produto> listaPaginada(Pageable pageable, ProdutoFilter filter) {
        if (filter.getNome().isEmpty()) {
            return produtoRepository.findAll(pageable);
        }
        return produtoRepository.findByNomeContainingIgnoreCase(filter.getNome().get(), pageable);
    }

    @Override
    public Produto criar(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarProduto(Long id, Produto produto) {
        Optional<Produto> produtoEncontrado = produtoRepository.findById(id);
        if (produtoEncontrado.isEmpty()) {
            throw new RuntimeException("implementar melhor dps");
        }
        // preguiça de pensar em um jeito melhor dps arrumo são 5 da manhã
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
