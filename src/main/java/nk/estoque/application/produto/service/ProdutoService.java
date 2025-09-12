package nk.estoque.application.produto.service;

import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.infraestructure.web.produto.ProdutoFilter;
import nk.estoque.domain.models.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface ProdutoService {

    Page<Produto> listaPaginada(Pageable pageable);

    Page<Produto> listaPaginada(Pageable pageable, ProdutoFilter filter);

    List<Produto> produtosPorId(List<Long> ids);

    Produto produtoPorId(Long id);

    Produto criar(Produto produto);

    Produto atualizarProduto(Long id, Produto produto);

    void atualizarProdutos(List<Produto> produtos);

    void deletarProduto(Long id);
}
