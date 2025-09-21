package nk.estoque.application.servico.useCase;

import jakarta.transaction.Transactional;
import nk.estoque.application.produto.service.ProdutoService;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.domain.repositories.ServicoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
    public Servico executarCriacao(Servico servico) {
        List<Long> ids = servico.getServicoProdutos()
                .stream().map(ServicoProdutos::getProdutoId).toList();

        List<Produto> produtos = produtoService.produtosPorId(ids);

        return calcularValorFinal(servico, produtos);
    }

    @Transactional
    public Servico executarAtualizacao(Servico servicoAntigo, Servico servicoNovo) {
        List<Long> ids = servicoNovo.getServicoProdutos()
                .stream().map(ServicoProdutos::getProdutoId).toList();
        List<Produto> produtos = produtoService.produtosPorId(ids);

        servicoAntigo.setNome(servicoNovo.getNome());
        servicoAntigo.setMaoDeObra(servicoNovo.getMaoDeObra());
        servicoAntigo.setServicoProdutos(servicoNovo.getServicoProdutos());

        return calcularValorFinal(servicoAntigo, produtos);
    }

    private Servico calcularValorFinal(Servico servico, List<Produto> produtos) {
        servico.setValorTotal(calculadora.calcular(servico, produtos));
        return servicoRepository.save(servico);
    }
}