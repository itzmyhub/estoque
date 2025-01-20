package nk.estoque.application.infraestructure.service.pedido;

import nk.estoque.application.infraestructure.entity.pedido.PedidoEntity;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.application.infraestructure.utils.exceptions.IdNaoEncontradoException;
import nk.estoque.application.infraestructure.persistence.repository.PedidoRepository;
import nk.estoque.application.infraestructure.service.cliente.ClienteService;
import nk.estoque.application.infraestructure.service.funcionario.FuncionarioService;
import nk.estoque.application.infraestructure.utils.exceptions.ProdutosInsuficientesException;
import nk.estoque.domain.pedido.Pedido;
import nk.estoque.application.infraestructure.service.produto.ProdutosService;
import nk.estoque.application.infraestructure.service.servico.ServicoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

        AtomicReference<BigDecimal> valorTotalProdutos = new AtomicReference<>(BigDecimal.ZERO);

        List<ServicoEntity> servicos = servicoService.servicosPorId(pedido.getServicosId());
        List<List<ServicoProdutosEntity>> servicoProdutosEntities = servicos.stream().map(ServicoEntity::getServicoProdutos).toList();
        List<ProdutoEntity> produtos = new ArrayList<>();

        pedido.getPedidoProdutos().forEach(pedidoProduto -> {
            if(pedidoProduto.getQuantidade() > produtosService.produtoPorId(pedidoProduto.getProdutoId()).getQuantidadeEstoque()) {
                throw new ProdutosInsuficientesException("Produto com ID " + pedidoProduto.getProdutoId() + " não possui quantidade suficiente em Estoque!");
            }
            produtos.add(pedido.atualizaQuantidadeProduto(produtosService.produtoPorId(pedidoProduto.getProdutoId()), pedidoProduto.getQuantidade()));
            valorTotalProdutos.set(produtos.stream()
                    .map(ProdutoEntity::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add).multiply(new BigDecimal(pedidoProduto.getQuantidade())));
        });

        BigDecimal valorTotalServicos = servicos.stream()
                .map(ServicoEntity::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);



        for (List<ServicoProdutosEntity> servicoProdutosEntity : servicoProdutosEntities) {
            for (ServicoProdutosEntity servicoProdutos : servicoProdutosEntity) {
                if(servicoProdutos.getQuantidade() > servicoProdutos.getProduto().getQuantidadeEstoque()) {
                    throw new ProdutosInsuficientesException("Produto com ID " + servicoProdutos.getProduto().getId() + " não possui quantidade suficiente em Estoque!");
                }
                produtos.add(pedido.atualizaQuantidadeProduto(servicoProdutos.getProduto(), servicoProdutos.getQuantidade()));
            }
        }

        PedidoEntity pedidoEntity = PedidoEntity.fromPedido(pedido, valorTotalServicos, valorTotalProdutos.get());

        pedidoEntity.setServicos(servicos);
        pedidoEntity.setFuncionario(funcionarioService.funcionarioPorId(pedido.getFuncionarioId()));
        pedidoEntity.setCliente(clienteService.clientePorId(pedido.getClienteId()));
        produtosService.atualizarProdutos(produtos);
        pedidoRepository.save(pedidoEntity);

        pedidoEntity.getPedidoProdutos().forEach(pedidoProdutos -> {
            pedidoProdutos.getId().setPedidoId(pedidoEntity.getId());
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
