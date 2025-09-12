package nk.estoque.infraestructure.persistence.pedido;

import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.infraestructure.entity.funcionario.FuncionarioEntity;
import nk.estoque.infraestructure.entity.pedido.PedidoEntity;
import nk.estoque.infraestructure.entity.servico.ServicoEntity;
import nk.estoque.infraestructure.persistence.pedidoProdutos.PedidoProdutosMapper;
import nk.estoque.infraestructure.persistence.servico.ServicoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    private final PedidoProdutosMapper pedidoProdutosMapper;

    public PedidoMapper(PedidoProdutosMapper pedidoProdutosMapper) {
        this.pedidoProdutosMapper = pedidoProdutosMapper;
    }

    public PedidoEntity toEntity(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();

        entity.setId(pedido.getId());
        entity.setValorAdicional(pedido.getValorAdicional());
        entity.setValorFinal(pedido.getValorFinal());
        entity.setDataHora(pedido.getDataHora());

        if (pedido.getServicosId() != null) {
            List<ServicoEntity> servicos = pedido.getServicosId().stream()
                    .map(id -> {
                        ServicoEntity se = new ServicoEntity();
                        se.setId(id);
                        return se;
                    })
                    .toList();
            entity.setServicos(servicos);
        }

        if (pedido.getPedidoProdutos() != null) {
            entity.setPedidoProdutos(
                    pedido.getPedidoProdutos().stream()
                            .map(pedidoProdutosMapper::toEntity)
                            .toList()
            );
        }

        if (pedido.getClienteId() != null) {
            ClienteEntity cliente = new ClienteEntity();
            cliente.setId(pedido.getClienteId());
            entity.setCliente(cliente);
        }

        if (pedido.getFuncionarioId() != null) {
            FuncionarioEntity funcionario = new FuncionarioEntity();
            funcionario.setId(pedido.getFuncionarioId());
            entity.setFuncionario(funcionario);
        }

        return entity;
    }

    // Converte Entity -> Domain
    public Pedido toDomain(PedidoEntity entity) {
        Pedido pedido = new Pedido();

        pedido.setId(entity.getId());
        pedido.setValorAdicional(entity.getValorAdicional());
        pedido.setValorFinal(entity.getValorFinal());
        pedido.setDataHora(entity.getDataHora());

        // Mapeia IDs de serviços
        if (entity.getServicos() != null) {
            pedido.setServicosId(
                    entity.getServicos().stream()
                            .map(ServicoEntity::getId)
                            .toList()
            );
        }

        // Mapeia produtos
        if (entity.getPedidoProdutos() != null) {
            pedido.setPedidoProdutos(
                    entity.getPedidoProdutos().stream()
                            .map(pedidoProdutosMapper::toDomain)
                            .toList()
            );
        }

        // Mapeia IDs de cliente e funcionário
        if (entity.getCliente() != null) {
            pedido.setClienteId(entity.getCliente().getId());
        }

        if (entity.getFuncionario() != null) {
            pedido.setFuncionarioId(entity.getFuncionario().getId());
        }

        return pedido;
    }
}

