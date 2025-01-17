package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.ProdutoEntity;
import nk.estoque.application.infraestructure.entity.ServicoEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosEntity;
import nk.estoque.application.infraestructure.entity.servico.ServicoProdutosKey;
import nk.estoque.application.infraestructure.repository.ServicoProdutosRepository;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.domain.servico.ServicoProdutos;
import nk.estoque.domain.servico.ServicoProdutosService;
import nk.estoque.domain.servico.ServicoService;
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
    public void criarServicoComProdutos(ServicoProdutos servicoProdutos, ServicoService servicoService) {

        ServicoProdutosEntity servicoProdutosEntity = new ServicoProdutosEntity();

        ProdutoEntity produto = produtosService.produtoPorId(servicoProdutos.getProdutoId());
        ServicoEntity servico = servicoService.servicoPorId(servicoProdutos.getServicoId());

        servicoProdutosEntity.setProduto(produto);
        servicoProdutosEntity.setServico(servico);
        servicoProdutosEntity.setId(new ServicoProdutosKey(produto.getId(), servico.getId()));
        servicoProdutosEntity.setQuantidade(servicoProdutos.getQuantidade());

        servicoProdutosRepository.save(servicoProdutosEntity);
    }
}
