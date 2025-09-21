package nk.estoque.infraestructure.entity.pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutosKey implements Serializable {

    @Column(name = "pedido_id")
    Long pedidoId;

    @Column(name = "produto_id")
    Long produtoId;

}
