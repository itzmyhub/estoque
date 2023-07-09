package NK.estoque.infraestructure.persistence;

import NK.estoque.domain.produto.Produto;
import NK.estoque.domain.produto.ProdutoValidation;
import NK.estoque.domain.produto.TodosProdutos;
import NK.estoque.infraestructure.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class TodosProdutosAdapter implements TodosProdutos {
    private final ProdutoRepository produtoRepository;

    public TodosProdutosAdapter(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<Produto> listaPaginada() {
        return produtoRepository.findAll();
    }

//    @Override
//    public void criarProduto(Produto produto) {
//        throw new RuntimeException("falta implementar");
//    }
//
//    @Override
//    public Produto atualizarProduto(Long id, Produto produto) { throw new RuntimeException("falta implementar"); }
}
