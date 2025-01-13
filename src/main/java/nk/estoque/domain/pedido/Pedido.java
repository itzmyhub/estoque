package nk.estoque.domain.pedido;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {
    private Long id;
    private BigDecimal valorAdicional;
    private List<Long> produtos;
    private Long funcionario;
    private Long cliente;
    private LocalDateTime dataHora;
    private List<Long> servicos;
    private BigDecimal valorFinal;

    // TODO implementar lógica aqui
}
