package nk.estoque.application.infraestructure.entity.servico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.domain.servico.ServicoProdutos;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
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
    ServicoEntity servico;

    int quantidade;

    public ServicoProdutosEntity() {

    }

    public static ServicoProdutosEntity fromServicoProduto(ServicoProdutos servicoProdutos) {
        return ServicoProdutosEntity.builder()
                .id(new ServicoProdutosKey(servicoProdutos.getProdutoId(), servicoProdutos.getServicoId()))
                .quantidade(servicoProdutos.getQuantidade())
                .build();
    }

    public static List<ServicoProdutosEntity> fromServicoProdutosList(List<ServicoProdutos> servicoProdutosList) {
        return servicoProdutosList
                .stream()
                .map(ServicoProdutosEntity::fromServicoProduto)
                .collect(Collectors.toList());
    }

}
