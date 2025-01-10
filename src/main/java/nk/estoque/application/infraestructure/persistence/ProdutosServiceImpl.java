package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.application.infraestructure.repository.ProdutoRepository;
import java.util.List;

public class ProdutosServiceImpl implements ProdutosService {
    private final ProdutoRepository produtoRepository;

    public ProdutosServiceImpl(ProdutoRepository produtoRepository) {
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
        Produto produtoEncontrado = produtoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Produto com ID " + id + " não encontrado"));

        produtoEncontrado.setNome(produto.getNome());
        produtoEncontrado.setValor(produto.getValor());
        produtoEncontrado.setQuantidadeItens(produto.getQuantidadeItens());
        produtoEncontrado.setCodigoProduto(produto.getCodigoProduto());

        return produtoRepository.save(produtoEncontrado);

    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
