package nk.estoque.application.infraestructure.web.servico;

import lombok.Data;
import nk.estoque.domain.servico.Servico;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ServicoPayload {

    private List<Long> produtos;

    private BigDecimal maoDeObra;

    private BigDecimal totalValue;

    public Servico toServico() {
        Servico servico = new Servico();
        servico.setMaoDeObra(maoDeObra);
        servico.setProdutos(produtos);
        servico.setTotalValue(totalValue);
        return servico;
    }

}
