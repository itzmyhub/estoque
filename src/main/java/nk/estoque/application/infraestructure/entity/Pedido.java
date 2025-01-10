package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private BigDecimal valorAdicional;

    @OneToMany
    private List<Produto> produtos;

    @OneToMany
    private List<Servico> servicos;

    @OneToOne
    private Funcionario funcionario;

    @OneToOne
    private Cliente cliente;

    private LocalDateTime dataHora;

    @Column
    private BigDecimal valorFinal;

}
