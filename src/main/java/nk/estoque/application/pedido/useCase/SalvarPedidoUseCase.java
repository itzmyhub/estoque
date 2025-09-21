package nk.estoque.application.pedido.useCase;

import jakarta.transaction.Transactional;
import nk.estoque.application.produto.service.ProdutoService;
import nk.estoque.application.servico.service.ServicoService;
import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedido.StatusPedido;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.repositories.PedidoRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SalvarPedidoUseCase {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ServicoService servicoService;
    private final CalculadoraValorPedido calculadora;

    public SalvarPedidoUseCase(
            PedidoRepository pedidoRepository,
            ServicoService servicoService,
            ProdutoService produtoService,
            CalculadoraValorPedido calculadora
    ) {
        this.pedidoRepository = pedidoRepository;
        this.servicoService = servicoService;
        this.produtoService = produtoService;
        this.calculadora = calculadora;
    }

    @Transactional
    public Pedido executar(Pedido pedido) {
        List<Servico> servicos = servicoService.servicosPorId(pedido.getServicosId());

        Map<Long, Integer> produtosComQuantidade = new HashMap<>();

        pedido.getPedidoProdutos().forEach(pp ->
                produtosComQuantidade.merge(pp.getProdutoId(), pp.getQuantidade(), Integer::sum)
        );

        servicos.stream()
                .flatMap(servico -> servico.getServicoProdutos().stream())
                .forEach(sp ->
                        produtosComQuantidade.merge(sp.getProdutoId(), sp.getQuantidade(), Integer::sum)
                );

        List<Produto> produtos = produtoService.produtosPorId(new ArrayList<>(produtosComQuantidade.keySet()));
        produtos.forEach(produto -> {
            produto.reservar(produtosComQuantidade.get(produto.getId()));
        });

        return criarPedido(pedido, servicos, produtos);
    }

    private Pedido criarPedido(Pedido pedido, List<Servico> servicos, List<Produto> produtos) {
        BigDecimal valorTotalServicos = servicos.stream()
                .map(Servico::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        pedido.setStatus(StatusPedido.CRIADO);
        pedido.setValorFinal(calculadora.calcular(pedido, valorTotalServicos, produtos));

        return pedidoRepository.save(pedido);
    }

}
