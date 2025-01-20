package nk.estoque.domain.pedido;

import lombok.Data;

@Data

public class PedidoProdutos {

    private Long pedidoId;

    private Long produtoId;

    private int quantidade;

}
