package nk.estoque.infraestructure.persistence.servicoProdutos;

import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosKey;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServicoProdutosMapper {

    public ServicoProdutosEntity toEntity(ServicoProdutos domain) {
        ServicoProdutosEntity entity = new ServicoProdutosEntity();
        entity.setId(new ServicoProdutosKey(domain.getServicoId(), domain.getProdutoId()));
        entity.setQuantidade(domain.getQuantidade());
        return entity;
    }

    public ServicoProdutos toDomain(ServicoProdutosEntity entity) {
        return new ServicoProdutos(
                entity.getProduto().getId(),
                entity.getServico().getId(),
                entity.getQuantidade()
        );
    }

    public List<ServicoProdutos> toDomainList(List<ServicoProdutosEntity> entities) {
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    public List<ServicoProdutosEntity> toEntityList(List<ServicoProdutos> domains) {
        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
