package nk.estoque.application.infraestructure.service.produto;

import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.application.infraestructure.web.produto.ProdutoFilter;
import nk.estoque.domain.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutosService {

    Page<ProdutoEntity> listaPaginada(Pageable pageable);

    Page<ProdutoEntity> listaPaginada(Pageable pageable, ProdutoFilter filter);

    List<ProdutoEntity> produtosPorId(List<Long> ids);

    ProdutoEntity produtoPorId(Long id);

    ProdutoEntity criar(Produto produto);

    ProdutoEntity atualizarProduto(Long id, Produto produto);

    void atualizarProdutos(List<ProdutoEntity> produtoEntities);

    void deletarProduto(Long id);
}
