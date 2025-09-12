package nk.estoque.application.pedido.service;

import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.domain.repositories.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Page<Pedido> listaPaginada(Pageable pageable) {
        return pedidoRepository.findAll(pageable);
    }

    @Override
    public Page<Pedido> listaPaginadaPorCliente(Long clienteId, Pageable pageable) {
        return pedidoRepository.findByClienteId(clienteId, pageable);
    }

    @Override
    public Page<Pedido> listaPaginadaPorFuncionario(Long funcionarioId, Pageable pageable) {
        return pedidoRepository.findByFuncionarioId(funcionarioId, pageable);
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        // JPA vai salvar pedido + produtos automaticamente
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));

        existente.setValorAdicional(pedido.getValorAdicional());
        existente.setServicosId(pedido.getServicosId());
        existente.setFuncionarioId(pedido.getFuncionarioId());
        existente.setClienteId(pedido.getClienteId());

        // atualiza a lista de produtos (JPA faz o merge)
        existente.getPedidoProdutos().clear();
        existente.getPedidoProdutos().addAll(pedido.getPedidoProdutos());

        return pedidoRepository.save(existente);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public Pedido pedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + pedidoId));
    }

    @Override
    public Pedido adicionarProduto(Long pedidoId, PedidoProdutos produto) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + pedidoId));

        produto.setPedidoId(pedidoId);

        pedido.getPedidoProdutos().add(produto);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido removerProduto(Long pedidoId, Long produtoId) {
        return null;
    }
}