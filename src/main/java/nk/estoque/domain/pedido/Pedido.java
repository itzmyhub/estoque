package nk.estoque.domain.pedido;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {
    private Long id;
    private BigDecimal valorAdicional;
    private Long funcionarioId;
    private Long clienteId;
    private List<Long> servicosId;
    private LocalDateTime dataHora;
    private List<PedidoProdutos> pedidoProdutos;
    private BigDecimal valorFinal;

    public boolean podeSerCriadoPedido() {
        return true;
    }

    public BigDecimal calculaValorFinal(BigDecimal valorTotalServicos, BigDecimal valorTotalProdutos) {
        return valorAdicional.add(valorTotalProdutos.add(valorTotalServicos));
    }

}
