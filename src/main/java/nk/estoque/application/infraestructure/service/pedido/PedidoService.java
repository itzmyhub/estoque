package nk.estoque.application.infraestructure.service.pedido;

import nk.estoque.application.infraestructure.entity.pedido.PedidoEntity;
import nk.estoque.domain.pedido.Pedido;

import java.util.List;

public interface PedidoService {

    List<PedidoEntity> listaPaginada();

    PedidoEntity criarPedido(Pedido pedido);

    PedidoEntity atualizarPedido(Long id, Pedido pedido);

    void deletarPedido(Long id);

    PedidoEntity pedidoPorId(Long pedidoId);
}
