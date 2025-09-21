package nk.estoque.application.pedido.service;

import nk.estoque.application.pedido.useCase.SalvarPedidoUseCase;
import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.domain.repositories.PedidoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final SalvarPedidoUseCase salvarPedidoUseCase;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, SalvarPedidoUseCase salvarPedidoUseCase) {
        this.pedidoRepository = pedidoRepository;
        this.salvarPedidoUseCase = salvarPedidoUseCase;
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
        return salvarPedidoUseCase.executar(pedido);
    }

    @Override
    public Pedido removerServico(Long pedidoId, Long servicoId) {

        return pedidoRepository.removerServico(pedidoId, servicoId);
    }

    @Override
    public Pedido adicionarServico(Long pedidoId, Long servicoId) {
        return null;
    }

    @Override
    public Pedido atualizarPedido(Long id, Pedido pedido) {
        Pedido existente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));

        existente.setValorAdicional(pedido.getValorAdicional());
        existente.setServicosId(pedido.getServicosId());
        existente.setFuncionarioId(pedido.getFuncionarioId());
        existente.setClienteId(pedido.getClienteId());

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