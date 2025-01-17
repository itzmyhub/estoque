package nk.estoque.application.infraestructure.payloads;

import lombok.Data;
import nk.estoque.domain.servico.ServicoProdutos;
import nk.estoque.domain.servico.Servico;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ServicoPayload {

    private BigDecimal maoDeObra;

    private BigDecimal totalValue;

    private List<ServicoProdutos> servicoProdutos;

    public Servico toServico() {
        Servico servico = new Servico();
        servico.setMaoDeObra(maoDeObra);
        servico.setTotalValue(totalValue);
        servico.setServicoProdutos(servicoProdutos);
        return servico;
    }

}
