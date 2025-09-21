package nk.estoque.domain.models.pedido;

import lombok.Data;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.domain.models.produto.Produto;

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
    private StatusPedido status;

    public void alterarStatus(StatusPedido novoStatus) {
        if (!status.podeTransitarPara(novoStatus)) {
            throw new IllegalStateException(
                    "Transição inválida de " + status + " para " + novoStatus
            );
        }
        this.status = novoStatus;
    }

}
