package nk.estoque.domain.model.produto;

import nk.estoque.application.infraestructure.entity.Produto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodosProdutos {
    List<Produto> listaPaginada();

    Produto criar(Produto produto);

    Produto atualizarProduto(Long id, Produto novoProduto);

    void deletarProduto(Long id);
}
