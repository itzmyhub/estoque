package nk.estoque.domain.pedido;

import nk.estoque.application.infraestructure.entity.Pedido;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PedidoService {

    List<Pedido> listaPaginada();

    Pedido criarPedido(Pedido pedido);

    Pedido atualizarPedido(Long id, Pedido pedido);

    void deletarPedido(Long id);
}
