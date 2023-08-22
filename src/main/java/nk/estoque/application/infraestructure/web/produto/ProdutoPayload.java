package nk.estoque.application.infraestructure.web.produto;
import jakarta.validation.constraints.*;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.Produto;
import nk.estoque.domain.model.produto.CodigoDeBarras;
import nk.estoque.domain.model.produto.Detalhe;
import nk.estoque.domain.model.produto.Marca;

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
    private Integer quantidadeEstoque;

    @NotNull(message = "O código do produto é obrigatório!")
    private Integer codigoProduto;

    private boolean emEstoque;

//    private Long id;
//    private String nome;
//    private BigDecimal valor;
//    private Integer quantidadeEstoque;
//    private CodigoDeBarras codigoDeBarras;
//    private Marca marca;
//    private Detalhe detalhe;
//
    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setCodigoProduto(codigoProduto);
        produto.setEmEstoque(emEstoque);
        return produto;
    }
}
