package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.produto.TodosProdutos;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;

public class TodosProdutosAdapter implements TodosProdutos {
    private final ProdutoRepository produtoRepository;

    public TodosProdutosAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> listaPaginada() {
        return produtoRepository.findAll();
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
