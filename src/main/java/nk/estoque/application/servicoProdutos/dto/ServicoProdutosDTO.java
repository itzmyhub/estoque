package nk.estoque.application.servicoProdutos.dto;

import lombok.Data;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;

@Data
public class ServicoProdutosDTO {

    private Long produtoId;
    private Long servicoId;
    private int quantidade;

    public ServicoProdutos toDomain() {
        ServicoProdutos sp = new ServicoProdutos();
        sp.setProdutoId(this.produtoId);
        sp.setServicoId(this.servicoId);
        sp.setQuantidade(this.quantidade);
        return sp;
    }

    public static ServicoProdutosDTO fromDomain(ServicoProdutos sp) {
        ServicoProdutosDTO dto = new ServicoProdutosDTO();
        dto.setProdutoId(sp.getProdutoId());
        dto.setServicoId(sp.getServicoId());
        dto.setQuantidade(sp.getQuantidade());
        return dto;
    }
}