package nk.estoque.application.infraestructure.entity.pedido;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Data
@AllArgsConstructor
public class PedidoProdutosKey implements Serializable {

    @Column(name = "produto_id")
    Long produtoId;

    @Column(name = "pedido_id")
    Long PedidoId;

    public PedidoProdutosKey() {

    }
}
