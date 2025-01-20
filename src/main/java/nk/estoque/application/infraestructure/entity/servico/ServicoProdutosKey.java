package nk.estoque.application.infraestructure.entity.servico;

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
public class ServicoProdutosKey implements Serializable {
    @Column(name = "produto_id")
    Long produtoId;

    @Column(name = "servico_id")
    Long servicoId;

    public ServicoProdutosKey() {

    }
}
