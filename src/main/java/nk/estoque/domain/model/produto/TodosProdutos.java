package nk.estoque.domain.model.produto;

import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.application.infraestructure.web.produto.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface TodosProdutos {
    Page<Produto> listaPaginada(Pageable pageable);

    Page<Produto> listaPaginada(Pageable pageable, ProdutoFilter filter);

    Produto criar(Produto produto);

    Produto atualizarProduto(Long id, Produto novoProduto);

    void deletarProduto(Long id);
}
