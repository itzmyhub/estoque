package nk.estoque.application.produto.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.produto.models.CodigoProduto;
import nk.estoque.domain.models.produto.models.Detalhe;
import nk.estoque.domain.models.produto.models.Marca;
import nk.estoque.domain.models.produto.models.TipoCodigoProduto;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {

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
    private String codigoProduto;

    private String tipoCodigo; // EAN13, UPC, QR_CODE

    private String marca;

    private String detalhe;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean emEstoque;

    public Produto toProduto() {
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setCodigoProduto(new CodigoProduto(codigoProduto, TipoCodigoProduto.valueOf(tipoCodigo != null ? tipoCodigo : "EAN13")));
        produto.setMarca(new Marca(marca));
        produto.setDetalhe(new Detalhe(detalhe));
        return produto;
    }

    public static ProdutoDTO fromProduto(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setValor(produto.getValor());
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setCodigoProduto(produto.getCodigoProduto().codigo());
        dto.setTipoCodigo(produto.getCodigoProduto().tipo().name());
        dto.setMarca(produto.getMarca().nome());
        dto.setDetalhe(produto.getDetalhe().descricao());
        dto.setEmEstoque(produto.isEmEstoque());
        return dto;
    }
}
