package nk.estoque.application.pedidoProdutos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;

@Data
public class PedidoProdutosDTO {

    private Long pedidoId;
    @NotNull(message = "produtoId é obrigatório")
    private Long produtoId;

    @NotNull @Min(1)
    private Integer quantidade;

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
