package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Pedido;
import nk.estoque.application.infraestructure.repository.PedidoRepository;
import nk.estoque.domain.pedido.PedidoService;

import java.util.List;
import java.util.Optional;

public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listaPaginada() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Optional<Pedido> trabalhoEncontrado = pedidoRepository.findById(id);
        if (trabalhoEncontrado.isEmpty()) {
            throw new RuntimeException("implementar melhor dps");
        }
        // preguiça de pensar em um jeito melhor dps arrumo são 5 da manhã
        pedido.setId(id);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
