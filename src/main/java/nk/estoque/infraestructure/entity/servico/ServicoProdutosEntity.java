package nk.estoque.infraestructure.entity.servico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicoProdutosEntity {
    @EmbeddedId
    ServicoProdutosKey id;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id")
    ProdutoEntity produto;

    @ManyToOne
    @MapsId("servicoId")
    @JoinColumn(name = "servico_id")
    @JsonIgnore
    @ToString.Exclude
    ServicoEntity servico;

    int quantidade;

}
