package nk.estoque.application.infraestructure.service.servico;

import nk.estoque.application.infraestructure.entity.produto.ProdutoEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.application.infraestructure.persistence.repository.ServicoProdutosRepository;
import nk.estoque.application.infraestructure.service.produto.ProdutosService;
import org.springframework.stereotype.Service;

@Service
public class ServicoProdutosServiceImpl implements ServicoProdutosService {

    private final ServicoProdutosRepository servicoProdutosRepository;

    private final ProdutosService produtosService;

    public ServicoProdutosServiceImpl(ServicoProdutosRepository servicoProdutosRepository, ProdutosService produtosService) {
        this.servicoProdutosRepository = servicoProdutosRepository;
        this.produtosService = produtosService;
    }

    @Override
    public void criarServicoComProdutos(ServicoProdutosEntity servicoProdutosEntity, ServicoService servicoService) {

        ProdutoEntity produto = produtosService.produtoPorId(servicoProdutosEntity.getId().getProdutoId());
        ServicoEntity servico = servicoService.servicoPorId(servicoProdutosEntity.getId().getServicoId());

        servicoProdutosEntity.setProduto(produto);
        servicoProdutosEntity.setServico(servico);

        servicoProdutosRepository.save(servicoProdutosEntity);
    }
}
