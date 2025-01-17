package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.PedidoEntity;
import nk.estoque.application.infraestructure.entity.ProdutoEntity;
import nk.estoque.application.infraestructure.entity.pedido.PedidoProdutosEntity;
import nk.estoque.application.infraestructure.entity.pedido.PedidoProdutosKey;
import nk.estoque.application.infraestructure.repository.PedidoProdutosRepository;
import nk.estoque.domain.pedido.PedidoProdutos;
import nk.estoque.domain.pedido.PedidoProdutosService;
import nk.estoque.domain.pedido.PedidoService;
import nk.estoque.domain.produto.ProdutosService;

public class PedidoProdutosServiceImpl implements PedidoProdutosService {

    private final PedidoProdutosRepository pedidoProdutosRepository;

    private final ProdutosService produtosService;

    public PedidoProdutosServiceImpl(PedidoProdutosRepository pedidoProdutosRepository, ProdutosService produtosService) {
        this.pedidoProdutosRepository = pedidoProdutosRepository;
        this.produtosService = produtosService;
    }

    @Override
    public void criaPedidoComProdutos(PedidoProdutos pedidoProdutos, PedidoService pedidoService) {
        PedidoProdutosEntity pedidoProdutosEntity = new PedidoProdutosEntity();

        ProdutoEntity produto = produtosService.produtoPorId(pedidoProdutos.getProdutoId());
        PedidoEntity pedido = pedidoService.pedidoPorId(pedidoProdutos.getPedidoId());

        pedidoProdutosEntity.setProduto(produto);
        pedidoProdutosEntity.setPedido(pedido);
        pedidoProdutosEntity.setId(new PedidoProdutosKey(produto.getId(), pedido.getId()));
        pedidoProdutosEntity.setQuantidade(pedidoProdutos.getQuantidade());

        pedidoProdutosRepository.save(pedidoProdutosEntity);
    }
}
