package nk.estoque.application.infraestructure.service.pedido;

import nk.estoque.application.infraestructure.entity.pedido.PedidoEntity;
import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.application.infraestructure.entity.pedido.PedidoProdutosEntity;
import nk.estoque.application.infraestructure.persistence.repository.PedidoProdutosRepository;
import nk.estoque.application.infraestructure.service.produto.ProdutosService;

public class PedidoProdutosServiceImpl implements PedidoProdutosService {

    private final PedidoProdutosRepository pedidoProdutosRepository;

    private final ProdutosService produtosService;

    public PedidoProdutosServiceImpl(PedidoProdutosRepository pedidoProdutosRepository, ProdutosService produtosService) {
        this.pedidoProdutosRepository = pedidoProdutosRepository;
        this.produtosService = produtosService;
    }

    @Override
    public void criaPedidoComProdutos(PedidoProdutosEntity pedidoProdutosEntity, PedidoService pedidoService) {

        ProdutoEntity produto = produtosService.produtoPorId(pedidoProdutosEntity.getId().getProdutoId());
        PedidoEntity pedido = pedidoService.pedidoPorId(pedidoProdutosEntity.getId().getPedidoId());

        pedidoProdutosEntity.setProduto(produto);
        pedidoProdutosEntity.setPedido(pedido);

        pedidoProdutosRepository.save(pedidoProdutosEntity);
    }
}
