package nk.estoque.application.pedidoProdutos.dto;

import lombok.Data;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;

@Data
public class PedidoProdutosDTO {

    private Long pedidoId;
    private Long produtoId;
    private int quantidade;

    public PedidoProdutos toDomain() {
        return new PedidoProdutos(pedidoId, produtoId, quantidade);
    }

    public static PedidoProdutosDTO fromDomain(PedidoProdutos pedidoProdutos) {
        PedidoProdutosDTO dto = new PedidoProdutosDTO();
        dto.setPedidoId(pedidoProdutos.getPedidoId());
        dto.setProdutoId(pedidoProdutos.getProdutoId());
        dto.setQuantidade(pedidoProdutos.getQuantidade());
        return dto;
    }
}
