package nk.estoque.application.servicoProdutos.service;

import nk.estoque.domain.models.servicoProdutos.ServicoProdutos;

import java.util.List;

public interface ServicoProdutosService {

    // Remove todas as relações de um serviço
    void deletarProdutosDoServico(Long servicoId);

    // Opcional: busca produtos de um serviço
    List<ServicoProdutos> listarProdutosDoServico(Long servicoId);
}
