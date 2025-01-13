package nk.estoque.domain.pedido;

import nk.estoque.application.infraestructure.entity.PedidoEntity;

import java.util.List;

public interface PedidoService {

    List<PedidoEntity> listaPaginada();

    PedidoEntity criarPedido(Pedido pedido);

    PedidoEntity atualizarPedido(Long id, Pedido pedido);

    void deletarPedido(Long id);
}
