package nk.estoque.application.infraestructure.web.produto;
import jakarta.validation.constraints.*;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.models.CodigoDeBarras;
import nk.estoque.domain.produto.models.Detalhe;
import nk.estoque.domain.produto.models.Marca;

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

    private ServicoProdutosEntity quantidadeEmServico;

    @NotNull(message = "O código do produto é obrigatório!")
    private CodigoDeBarras codigoDeBarras;

    private Marca marca;

    private Detalhe detalhe;

    private boolean emEstoque;

    public Produto toProduto() {
        Produto produto = new Produto(nome, valor, quantidadeEstoque, codigoDeBarras, marca, detalhe);
        produto.temQuantidadeEmEstoque();
        return produto;
    }
}
