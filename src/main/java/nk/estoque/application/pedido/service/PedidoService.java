package nk.estoque.application.pedido.service;

import nk.estoque.domain.models.pedido.Pedido;
import nk.estoque.domain.models.pedidoProdutos.PedidoProdutos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    Page<Pedido> listaPaginada(Pageable pageable);

    Page<Pedido> listaPaginadaPorCliente(Long clienteId, Pageable pageable);

    Page<Pedido> listaPaginadaPorFuncionario(Long funcionarioId, Pageable pageable);

    Pedido criarPedido(Pedido pedido);

    Pedido removerServico(Long pedidoId, Long servicoId);

    Pedido adicionarServico(Long pedidoId, Long servicoId);

    Pedido atualizarPedido(Long id, Pedido pedido);

    void deletarPedido(Long id);

    Pedido pedidoPorId(Long pedidoId);

    Pedido adicionarProduto(Long pedidoId, PedidoProdutos produtos);

    Pedido removerProduto(Long pedidoId, Long produtoId);
}
