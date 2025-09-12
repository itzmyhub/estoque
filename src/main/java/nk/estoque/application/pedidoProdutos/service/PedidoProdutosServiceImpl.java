package nk.estoque.application.pedidoProdutos.service;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;

import nk.estoque.domain.repositories.PedidoProdutosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoProdutosServiceImpl implements PedidoProdutosService {

    private final PedidoProdutosRepository pedidoProdutosRepository;

    public PedidoProdutosServiceImpl(
            PedidoProdutosRepository pedidoProdutosRepository
    ) {
        this.pedidoProdutosRepository = pedidoProdutosRepository;
    }


    @Override
    public void deletarProdutosDoPedido(Long pedidoId) {
        pedidoProdutosRepository.deleteByPedidoId(pedidoId);
    }

    @Override
    public List<PedidoProdutos> listarProdutosDoPedido(Long pedidoId) {
        return pedidoProdutosRepository.findByPedidoId(pedidoId);
    }
}

