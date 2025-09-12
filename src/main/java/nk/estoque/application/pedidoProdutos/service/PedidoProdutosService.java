package nk.estoque.application.pedidoProdutos.service;

import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;

import java.util.List;

public interface PedidoProdutosService {
    /**
     * Remove todos os produtos associados a um pedido.
     */
    void deletarProdutosDoPedido(Long pedidoId);

    /**
     * Lista todos os produtos associados a um pedido.
     */
    List<PedidoProdutos> listarProdutosDoPedido(Long pedidoId);
}
