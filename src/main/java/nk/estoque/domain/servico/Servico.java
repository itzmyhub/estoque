package nk.estoque.domain.servico;

import lombok.Data;
import nk.estoque.application.infraestructure.entity.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Servico {
    private Long id;
    private BigDecimal maoDeObra;
    private List<ServicoProdutos> servicoProdutos;
    private BigDecimal totalValue;

    public BigDecimal calcularTotalValue(List<ProdutoEntity> produtoEntities) {

        BigDecimal soma = BigDecimal.ZERO;

        for (int i = 0; i < produtoEntities.size(); i++) {
            ProdutoEntity produtoEntity = produtoEntities.get(i);

                int quantidadeDeProdutos = this.servicoProdutos.get(i).getQuantidade();

                BigDecimal valorTotalProduto = produtoEntity.getValor().multiply(new BigDecimal(quantidadeDeProdutos));
                soma = soma.add(valorTotalProduto);
        }

        return soma.add(this.maoDeObra);
    }

//    public void logicaDeVenda(ProdutoEntity produtoEntity) {
//        //TODO FAZER A LOGICA DE VENDA, A VER
//        Produto produto = new Produto(produtoEntity.getNome(),
//                produtoEntity.getValor(),
//                produtoEntity.getQuantidadeEstoque(),
//                new CodigoDeBarras(produtoEntity.getCodigoDeBarras(), TipoCodigoDeBarras.EAN13),
//                new Marca(produtoEntity.getMarca()),
//                new Detalhe(produtoEntity.getDetalhe()));
//    }

}
