package nk.estoque.application.infraestructure.web.produto;
import jakarta.validation.constraints.*;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.Produto;

import java.math.BigDecimal;

@Data
public class ProdutoPayload {

    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal valor;

    @NotNull(message = "A quantidade é obrigatória!")
    @PositiveOrZero(message = "A quantidade de itens deve ser maior ou igual a zero")
    private Integer quantidadeItens;

    @NotNull(message = "O código do produto é obrigatório!")
    private Integer codigoProduto;

    private boolean emEstoque;

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setQuantidadeItens(quantidadeItens);
        produto.setCodigoProduto(codigoProduto);
        produto.emEstoque();
        return produto;
    }
}
