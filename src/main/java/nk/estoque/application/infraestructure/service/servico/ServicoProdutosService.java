package nk.estoque.application.infraestructure.service.servico;

import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;

public interface ServicoProdutosService {

    void criarServicoComProdutos(ServicoProdutosEntity servicoProdutosEntity, ServicoService servicoService);
}
