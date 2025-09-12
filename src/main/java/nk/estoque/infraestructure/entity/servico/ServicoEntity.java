package nk.estoque.infraestructure.entity.servico;

import jakarta.persistence.*;
import lombok.*;
import nk.estoque.domain.models.servico.Servico;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicoProdutosEntity> servicoProdutos;

    @Column(precision = 15, scale = 2)
    private BigDecimal maoDeObra;

    @Column(name = "total_value", precision = 15, scale = 2)
    private BigDecimal totalValue;


}