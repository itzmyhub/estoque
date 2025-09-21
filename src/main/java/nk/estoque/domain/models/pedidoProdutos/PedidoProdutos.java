package nk.estoque.domain.models.pedidoProdutos;

import lombok.Data;

@Data
public class PedidoProdutos {

    private Long pedidoId;

    private Long produtoId;

    private int quantidade;

    public PedidoProdutos(Long pedidoId, Long produtoId, int quantidade) {
        this.produtoId = produtoId;
        this.pedidoId = pedidoId;
        this.quantidade = quantidade;
    }
}
