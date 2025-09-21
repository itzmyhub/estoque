package nk.estoque.domain.models.servicoProdutos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoProdutos {

    private Long produtoId;
    private Long servicoId;
    private int quantidade;

}
