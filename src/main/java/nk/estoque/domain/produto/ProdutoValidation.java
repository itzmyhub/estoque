package NK.estoque.domain.produto;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProdutoValidation {

    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal valor;

    @NotBlank(message = "A quantidade é obrigatória!")
    @PositiveOrZero(message = "A quantidade de itens deve ser maior ou igual a zero")
    private int quantidadeItens;

    @NotBlank(message = "O código do produto é obrigatório!")
    private Long codigoProduto;

    @NotBlank(message = "Campo obrigatório!")
    private boolean emEstoque;
}
