package nk.estoque.application.infraestructure.service.produto;

import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.domain.produto.Produto;

import java.util.List;

public interface ProdutosService {
    List<ProdutoEntity> listaPaginada();

    List<ProdutoEntity> produtosPorId(List<Long> ids);

    ProdutoEntity produtoPorId(Long id);

    ProdutoEntity criar(Produto produto);

    ProdutoEntity atualizarProduto(Long id, Produto produto);

    void deletarProduto(Long id);
}
