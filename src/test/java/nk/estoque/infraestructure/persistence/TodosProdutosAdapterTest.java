package nk.estoque.infraestructure.persistence;

import NK.estoque.domain.produto.Produto;
import NK.estoque.infraestructure.persistence.TodosProdutosAdapter;
import NK.estoque.infraestructure.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TodosProdutosAdapterTest {

    private final ProdutoRepository produtoRepository;

    public TodosProdutosAdapterTest(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Test
    void deve_retornar_todos_os_produtos() {
        Produto produto1 = new Produto("Evaporador", new BigDecimal("1000.0"), 1, 100, true);
        produtoRepository.save(produto1);
        TodosProdutosAdapter todosProdutos = new TodosProdutosAdapter(produtoRepository);
        when(todosProdutos.listaPaginada()).thenReturn(List.of(produto1));

        Produto primeiroProduto = todosProdutos.listaPaginada().get(0);

        assertEquals(produto1.getNome(), primeiroProduto.getNome());
    }

}
