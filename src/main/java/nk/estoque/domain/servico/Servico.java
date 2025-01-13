package nk.estoque.domain.servico;

import lombok.Data;
import nk.estoque.application.infraestructure.entity.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Servico {
    private Long id;
    private List<Long> produtos;
    private BigDecimal maoDeObra;
    private BigDecimal totalValue;

    public BigDecimal calcularTotalValue(List<ProdutoEntity> produtos) {
        BigDecimal totalProdutos = produtos.stream()
                .map(produto -> produto.getValor().multiply(new BigDecimal(produto.getQuantidadeEstoque())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalProdutos.add(maoDeObra);
    }
}
