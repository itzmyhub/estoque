package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.servico.Servico;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    private List<ProdutoEntity> produtos;

    @Column
    private BigDecimal maoDeObra;

    @Column
    private BigDecimal totalValue;

    public static ServicoEntity fromServico(Servico servico) {
        return ServicoEntity.builder()
                .id(servico.getId())
                .maoDeObra(servico.getMaoDeObra())
                .build();
    }

    public static List<ServicoEntity> fromServicos(List<Servico> servicos) {
        return servicos.stream()
                .map(ServicoEntity::fromServico)
                .collect(Collectors.toList());
    }
}
