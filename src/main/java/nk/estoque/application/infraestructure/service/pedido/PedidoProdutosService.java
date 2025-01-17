package nk.estoque.application.infraestructure.service.pedido;

import nk.estoque.domain.pedido.PedidoProdutos;

public interface PedidoProdutosService {

    void criaPedidoComProdutos(PedidoProdutos pedidoProdutos, PedidoService pedidoService);
}
