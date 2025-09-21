package nk.estoque.infraestructure.persistence.produto;

import nk.estoque.domain.models.produto.Produto;
import nk.estoque.domain.models.produto.models.CodigoProduto;
import nk.estoque.domain.models.produto.models.Detalhe;
import nk.estoque.domain.models.produto.models.Marca;
import nk.estoque.domain.models.produto.models.TipoCodigoProduto;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toDomain(ProdutoEntity entity) {
        if (entity == null) return null;

        Produto produto = new Produto();
        produto.setId(entity.getId());
        produto.setNome(entity.getNome());
        produto.setValor(entity.getValor());
        produto.setQuantidadeEstoque(entity.getQuantidadeEstoque());

        if (entity.getCodigoProduto() != null) {
            produto.setCodigoProduto(new CodigoProduto(entity.getCodigoProduto(), TipoCodigoProduto.EAN13));
        }

        produto.setMarca(entity.getMarca() != null ? new Marca(entity.getMarca()) : null);
        produto.setDetalhe(entity.getDetalhe() != null ? new Detalhe(entity.getDetalhe()) : null);

        return produto;
    }

    public ProdutoEntity toEntity(Produto produto) {
        if (produto == null) return null;

        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(produto.getId());
        entity.setNome(produto.getNome());
        entity.setValor(produto.getValor());
        entity.setQuantidadeEstoque(produto.getQuantidadeEstoque() != null ? produto.getQuantidadeEstoque() : 0);

        if (produto.getCodigoProduto() != null) {
            entity.setCodigoProduto(produto.getCodigoProduto().codigo());
            entity.setTipoCodigo(produto.getCodigoProduto().tipo().name());
        }

        entity.setMarca(produto.getMarca() != null ? produto.getMarca().nome() : null);
        entity.setDetalhe(produto.getDetalhe() != null ? produto.getDetalhe().descricao() : null);
        return entity;
    }
}
