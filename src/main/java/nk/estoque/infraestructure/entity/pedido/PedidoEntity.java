package nk.estoque.infraestructure.entity.pedido;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.models.pedido.StatusPedido;
import nk.estoque.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.infraestructure.entity.servico.ServicoEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private BigDecimal valorAdicional;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoProdutosEntity> pedidoProdutos = new ArrayList<>();

    @ManyToMany
    private List<ServicoEntity> servicos;

    @ManyToOne(optional = false,cascade=CascadeType.REMOVE)
    private FuncionarioEntity funcionario;

    @ManyToOne(optional = false,cascade=CascadeType.REMOVE)
    private ClienteEntity cliente;

    @Column(name = "data_e_hora", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Column
    private BigDecimal valorFinal;

    @PrePersist
    public void prePersist() {
        if (dataHora == null) {
            dataHora = LocalDateTime.now();
        }
    }
}
