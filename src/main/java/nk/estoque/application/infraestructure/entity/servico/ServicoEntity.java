package nk.estoque.application.infraestructure.entity.servico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.servico.Servico;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "servico", cascade=CascadeType.REMOVE)
    List<ServicoProdutosEntity> servicoProdutos;

    @Column
    private BigDecimal maoDeObra;

    @Column
    private BigDecimal totalValue;

    public static ServicoEntity fromServico(Servico servico) {
        return ServicoEntity.builder()
                .id(servico.getId())
                .maoDeObra(servico.getMaoDeObra())
                .servicoProdutos(ServicoProdutosEntity.fromServicoProdutosList(servico.getServicoProdutos()))
                .build();
    }
}
