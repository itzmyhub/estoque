package nk.estoque.domain.servico;

import lombok.Data;

@Data
public class ServicoProdutos {

    Long produtoId;

    Long servicoId;

    int quantidade;
}
