package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.PedidoEntity;
import nk.estoque.application.infraestructure.entity.ProdutoEntity;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.application.infraestructure.repository.PedidoRepository;
import nk.estoque.domain.cliente.ClienteService;
import nk.estoque.domain.funcionario.FuncionarioService;
import nk.estoque.domain.pedido.Pedido;
import nk.estoque.domain.pedido.PedidoProdutosService;
import nk.estoque.domain.pedido.PedidoService;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.domain.servico.ServicoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ServicoService servicoService;
    private final ProdutosService produtosService;
    private final ClienteService clienteService;
    private final FuncionarioService funcionarioService;
    private final PedidoProdutosService pedidoProdutosService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ServicoService servicoService, ProdutosService produtosService, ClienteService clienteService, FuncionarioService funcionarioService, PedidoProdutosService pedidoProdutosService) {
        this.pedidoRepository = pedidoRepository;
        this.servicoService = servicoService;
        this.produtosService = produtosService;
        this.clienteService = clienteService;
        this.funcionarioService = funcionarioService;
        this.pedidoProdutosService = pedidoProdutosService;
    }

    @Override
    public List<PedidoEntity> listaPaginada() {
        return pedidoRepository.findAll();
    }

    @Override
    public PedidoEntity criarPedido(Pedido pedido) {

        List<ProdutoEntity> produtos = new ArrayList<>();

        PedidoEntity pedidoEntity = PedidoEntity.fromPedido(pedido);
        pedidoEntity.setServicos(servicoService.servicosPorId(pedido.getServicosId()));
        pedidoEntity.setFuncionario(funcionarioService.funcionarioPorId(pedido.getFuncionarioId()));
        pedidoEntity.setCliente(clienteService.clientePorId(pedido.getClienteId()));
        pedidoRepository.save(pedidoEntity);

        pedido.getPedidoProdutos().forEach(pedidoProdutos -> {
            pedidoProdutos.setPedidoId(pedidoEntity.getId());
            produtos.add(produtosService.produtoPorId(pedidoProdutos.getProdutoId()));
        });

        pedido.getPedidoProdutos().forEach(pedidoProdutos -> {
            pedidoProdutosService.criaPedidoComProdutos(pedidoProdutos, this);
        });

        return pedidoEntity;
    }

    @Override
    public PedidoEntity atualizarPedido(Long id, Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Pedido com ID " + id + " não encontrado"));

        //TODO IMPLEMENTAR A ATUALIZAÇÃO DO PEDIDO

        return pedidoRepository.save(pedidoEntity);
    }

    @Override
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public PedidoEntity pedidoPorId(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IdNaoEncontradoException("Pedido com ID " + pedidoId + " não encontrado"));
    }
}
