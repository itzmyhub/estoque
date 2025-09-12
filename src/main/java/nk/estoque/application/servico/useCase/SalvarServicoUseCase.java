package nk.estoque.application.servico.useCase;

import jakarta.transaction.Transactional;
import nk.estoque.application.produto.service.ProdutoService;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.domain.repositories.ServicoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SalvarServicoUseCase {

    private final ServicoRepository servicoRepository;
    private final ProdutoService produtoService;
    private final CalculadoraValorServico calculadora;

    public SalvarServicoUseCase(
            ServicoRepository servicoRepository,
            ProdutoService produtoService,
            CalculadoraValorServico calculadora
    ) {
        this.servicoRepository = servicoRepository;
        this.produtoService = produtoService;
        this.calculadora = calculadora;
    }

    @Transactional
    public Servico executar(Servico servico) {
        validar(servico);

        Map<Long, Produto> produtoMap = produtoService.produtosPorId(
                servico.getServicoProdutos().stream()
                        .map(ServicoProdutos::getProdutoId)
                        .toList()
        ).stream().collect(Collectors.toMap(Produto::getId, Function.identity()));

        verificarProdutosExistentes(servico, produtoMap);

        servico.getServicoProdutos().forEach(sp ->
                produtoMap.get(sp.getProdutoId()).reservar(sp.getQuantidade())
        );

        produtoService.atualizarProdutos(new ArrayList<>(produtoMap.values()));

        servico.setValorTotal(calculadora.calcular(servico, produtoMap));

        return servicoRepository.save(servico);
    }

    private void validar(Servico servico) {
        if (servico.getServicoProdutos() == null || servico.getServicoProdutos().isEmpty()) {
            throw new IllegalArgumentException("Um serviço precisa ter ao menos um produto.");
        }
    }

    private void verificarProdutosExistentes(Servico servico, Map<Long, Produto> produtoMap) {
        if (produtoMap.size() != servico.getServicoProdutos().size()) {
            throw new IllegalStateException("Um ou mais produtos não foram encontrados para este serviço.");
        }
    }
}