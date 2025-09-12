package nk.estoque.domain.repositories;

import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;

import java.util.List;

public interface PedidoProdutosRepository {

    /**
     * Salva uma lista de produtos de um pedido.
     *
     * @param pedidoProdutos lista de produtos do pedido
     * @param pedidoId ID do pedido
     */
    void saveAll(List<PedidoProdutos> pedidoProdutos, Long pedidoId);

    /**
     * Remove todos os produtos de um pedido específico.
     *
     * @param pedidoId ID do pedido
     */
    void deleteByPedidoId(Long pedidoId);

    /**
     * Retorna todos os produtos de um pedido específico.
     *
     * @param pedidoId ID do pedido
     * @return lista de produtos do pedido
     */
    List<PedidoProdutos> findByPedidoId(Long pedidoId);
}

