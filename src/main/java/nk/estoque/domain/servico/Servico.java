package nk.estoque.domain.servico;

import lombok.Data;
import nk.estoque.domain.produto.Produto;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Servico {
    private Long id;
    private List<Produto> produtos;
    private BigDecimal maoDeObra;
    private BigDecimal totalValue;
}
