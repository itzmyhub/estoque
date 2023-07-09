package NK.estoque.domain.produto;
;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodosProdutos {
    List<Produto> listaPaginada();

//    void criarProduto(Produto produto);
//
//    Produto atualizarProduto(Long id, Produto produto);
}
