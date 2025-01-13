package nk.estoque.domain.produto;

import nk.estoque.application.infraestructure.entity.ProdutoEntity;

import java.util.List;

public interface ProdutosService {
    List<ProdutoEntity> listaPaginada();

    List<ProdutoEntity> produtosPorId(List<Long> ids);

    ProdutoEntity criar(Produto produto);

    ProdutoEntity atualizarProduto(Long id, Produto produto);

    void deletarProduto(Long id);
}
