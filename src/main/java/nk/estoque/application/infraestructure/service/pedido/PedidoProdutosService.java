package nk.estoque.application.infraestructure.service.pedido;

import nk.estoque.application.infraestructure.entity.pedido.PedidoProdutosEntity;

public interface PedidoProdutosService {

    void criaPedidoComProdutos(PedidoProdutosEntity pedidoProdutosEntity, PedidoService pedidoService);
}
