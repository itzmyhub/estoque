package nk.estoque.application.infraestructure.service.servico;

import nk.estoque.domain.servico.ServicoProdutos;

public interface ServicoProdutosService {

    void criarServicoComProdutos(ServicoProdutos servicoProdutos, ServicoService servicoService);
}
