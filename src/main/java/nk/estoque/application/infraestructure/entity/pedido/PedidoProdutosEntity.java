package nk.estoque.application.infraestructure.entity.pedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.domain.pedido.PedidoProdutos;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PedidoProdutosEntity {
    @EmbeddedId
    PedidoProdutosKey id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    ProdutoEntity produto;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id")
    @JsonIgnore
    PedidoEntity pedido;

    int quantidade;

    public PedidoProdutosEntity() {

    }

    public static PedidoProdutosEntity fromPedidoProdutos(PedidoProdutos pedidoProdutos) {
        return PedidoProdutosEntity.builder()
                .id(new PedidoProdutosKey(pedidoProdutos.getProdutoId(), pedidoProdutos.getPedidoId()))
                .quantidade(pedidoProdutos.getQuantidade())
                .build();
    }

    public static List<PedidoProdutosEntity> fromPedidoProdutosList(List<PedidoProdutos> pedidoProdutosList) {
        return pedidoProdutosList
                .stream()
                .map(PedidoProdutosEntity::fromPedidoProdutos)
                .toList();
    }
}
