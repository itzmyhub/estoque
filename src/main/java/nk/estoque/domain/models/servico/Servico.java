package nk.estoque.domain.models.servico;

import lombok.Data;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class Servico {
    private Long id;
    private String nome;
    private BigDecimal maoDeObra;
    private List<ServicoProdutos> servicoProdutos;
    private BigDecimal valorTotal;

    public void atualizar(Servico servico) {
        this.nome = servico.getNome();
        this.maoDeObra = servico.getMaoDeObra();
    }
}
