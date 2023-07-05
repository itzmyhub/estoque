package nk.estoque.infraestructure.persistence;

import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.TodosProdutos;

import java.util.List;

public class TodosProdutosAdapter implements TodosProdutos {
    @Override
    public List<Produto> listaPaginada() {
        throw new RuntimeException("falta implementar");
    }
}
