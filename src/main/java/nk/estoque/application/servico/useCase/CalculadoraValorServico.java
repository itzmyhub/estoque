package nk.estoque.application.servico.useCase;

import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.infraestructure.utils.exceptions.IdNaoEncontradoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CalculadoraValorServico {

    public BigDecimal calcular(Servico servico, List<Produto> produtos) {
        BigDecimal soma = BigDecimal.ZERO;

        for (ServicoProdutos sp : servico.getServicoProdutos()) {
            Produto produto = produtos.stream()
                    .filter(p -> p.getId().equals(sp.getProdutoId()))
                    .findFirst()
                    .orElseThrow(() -> new IdNaoEncontradoException(
                            "Produto não encontrado " + sp.getProdutoId()));

            soma = soma.add(
                    produto.getValor().multiply(BigDecimal.valueOf(sp.getQuantidade()))
            );
        }

        return soma.add(
                Optional.ofNullable(servico.getMaoDeObra()).orElse(BigDecimal.ZERO)
        );
    }
}
