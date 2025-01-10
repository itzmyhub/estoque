package nk.estoque.application.infraestructure.web.servico;

import lombok.Data;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.application.infraestructure.entity.Servico;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ServicoPayload {

    private List<Produto> produtos;

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
