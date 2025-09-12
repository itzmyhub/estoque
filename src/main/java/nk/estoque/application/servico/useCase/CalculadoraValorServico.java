package nk.estoque.application.servico.useCase;

import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.infraestructure.utils.exceptions.IdNaoEncontradoException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class CalculadoraValorServico {

    public BigDecimal calcular(Servico servico, Map<Long, Produto> produtoMap) {
        BigDecimal soma = BigDecimal.ZERO;
        for (ServicoProdutos sp : servico.getServicoProdutos()) {
            Produto produto = produtoMap.get(sp.getProdutoId());
            if (produto == null) {
                throw new IdNaoEncontradoException("produto não encontrado " + sp.getProdutoId());
            }
            soma = soma.add(produto.getValor().multiply(BigDecimal.valueOf(sp.getQuantidade())));
        }
        return soma.add(Optional.ofNullable(servico.getMaoDeObra()).orElse(BigDecimal.ZERO));
    }
}
