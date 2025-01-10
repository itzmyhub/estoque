package nk.estoque.domain.pedido;

import nk.estoque.domain.servico.Servico;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private Long id;
    private BigDecimal valorAdicional;
    private String funcionario;
    private String cliente;
    private LocalDateTime dataHora;
    private List<Servico> servicos;

    // TODO implementar lógica aqui
}
