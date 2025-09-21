package nk.estoque.infraestructure.entity.pedido;

import jakarta.persistence.*;
import lombok.*;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PedidoProdutosEntity {

    @EmbeddedId
    PedidoProdutosKey id = new PedidoProdutosKey();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", nullable = false)
    PedidoEntity pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id", nullable = false)
    ProdutoEntity produto;

    @Column(nullable = false)
    int quantidade;
}
