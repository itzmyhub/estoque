package nk.estoque.infraestructure.entity.servico;

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
public class ServicoProdutosKey implements Serializable {
    @Column(name = "produto_id")
    Long produtoId;

    @Column(name = "servico_id")
    Long servicoId;

}
