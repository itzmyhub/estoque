package nk.estoque.infraestructure.persistence.servico;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import nk.estoque.domain.models.servico.Servico;
import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;
import nk.estoque.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.infraestructure.entity.servico.ServicoEntity;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.infraestructure.entity.servico.ServicoProdutosKey;
import nk.estoque.infraestructure.utils.exceptions.IdNaoEncontradoException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ServicoMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public ServicoEntity toEntity(Servico servico) {
        if (servico == null) return null;

        ServicoEntity entity = ServicoEntity.builder()
                .id(servico.getId())
                .nome(servico.getNome())
                .maoDeObra(servico.getMaoDeObra())
                .totalValue(servico.getValorTotal())
                .build();

        if (servico.getServicoProdutos() != null) {
            List<ServicoProdutosEntity> produtosEntity = servico.getServicoProdutos().stream()
                    .map(sp -> toEntity(sp, entity))
                    .toList();
            entity.setServicoProdutos(produtosEntity);
        }

        return entity;
    }

    private ServicoProdutosEntity toEntity(ServicoProdutos sp, ServicoEntity servicoEntity) {
        ServicoProdutosKey key = new ServicoProdutosKey();
        key.setServicoId(servicoEntity.getId());
        key.setProdutoId(sp.getProdutoId());

        ProdutoEntity produtoRef = entityManager.getReference(ProdutoEntity.class, sp.getProdutoId());

        return ServicoProdutosEntity.builder()
                .id(key)
                .servico(servicoEntity)
                .produto(produtoRef)
                .quantidade(sp.getQuantidade())
                .build();
    }

    public Servico toDomain(ServicoEntity entity) {
        if (entity == null) return null;

        Servico servico = new Servico();
        servico.setId(entity.getId());
        servico.setNome(entity.getNome());
        servico.setMaoDeObra(entity.getMaoDeObra());
        servico.setValorTotal(entity.getTotalValue());

        if (entity.getServicoProdutos() != null) {
            servico.setServicoProdutos(
                    entity.getServicoProdutos().stream()
                            .map(this::toDomain)
                            .toList()
            );
        }

        return servico;
    }

    private ServicoProdutos toDomain(ServicoProdutosEntity entity) {
        ServicoProdutos sp = new ServicoProdutos();
        sp.setProdutoId(entity.getProduto().getId());
        sp.setServicoId(entity.getServico().getId());
        sp.setQuantidade(entity.getQuantidade());
        return sp;
    }
}
