package nk.estoque.application.infraestructure.payloads;
import jakarta.validation.constraints.*;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.domain.produto.Produto;
import nk.estoque.domain.produto.models.CodigoProduto;
import nk.estoque.domain.produto.models.Detalhe;
import nk.estoque.domain.produto.models.Marca;
import nk.estoque.domain.produto.models.TipoCodigoProduto;

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
    private String codigoProduto;

    private String marca;

    private String detalhe;

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setCodigoProduto(new CodigoProduto(codigoProduto, TipoCodigoProduto.EAN13));
        produto.setMarca(new Marca(marca));
        produto.setDetalhe(new Detalhe(detalhe));
        produto.temQuantidadeEmEstoque();
        return produto;
    }
}
