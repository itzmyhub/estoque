package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.ProdutoEntity;
import nk.estoque.application.infraestructure.entity.ServicoEntity;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.application.infraestructure.repository.ServicoRepository;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.domain.servico.Servico;
import nk.estoque.domain.servico.ServicoProdutosService;
import nk.estoque.domain.servico.ServicoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository servicoRepository;

    private final ProdutosService produtosService;

    private final ServicoProdutosService servicoProdutosService;

    public ServicoServiceImpl(ServicoRepository servicoRepository, ProdutosService produtosService, ServicoProdutosService servicoProdutosService) {
        this.servicoRepository = servicoRepository;
        this.produtosService = produtosService;
        this.servicoProdutosService = servicoProdutosService;
    }

    @Override
    public List<ServicoEntity> listaPaginada() {
        return servicoRepository.findAll();
    }

    @Override
    public List<ServicoEntity> servicosPorId(List<Long> ids) {
        return servicoRepository.findAllById(ids);
    }

    @Override
    public ServicoEntity servicoPorId(Long id) { return servicoRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("Serviço com ID " + id + " não encontrado"));}

    @Override
    public ServicoEntity criar(Servico servico) {

        List<ProdutoEntity> produtos = new ArrayList<>();

        ServicoEntity servicoEntity = ServicoEntity.fromServico(servico);
        servicoRepository.save(servicoEntity);

        servico.getServicoProdutos().forEach(servicoProdutos -> {
            servicoProdutos.setServicoId(servicoEntity.getId());
            produtos.add(produtosService.produtoPorId(servicoProdutos.getProdutoId()));
        });

        servicoEntity.setTotalValue(servico.calcularTotalValue(produtos));

        servico.getServicoProdutos().forEach(servicoProdutos -> {
            servicoProdutosService.criarServicoComProdutos(servicoProdutos, this);
        });

        return servicoEntity;
    }

    @Override
    public ServicoEntity atualizarServico(Long id, Servico servico) {
        ServicoEntity servicoEntity = servicoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Serviço com ID " + id + " não encontrado"));

        //TODO IMPLEMENTAR A ATUALIZAÇÃO DO SERVIÇO

        return servicoRepository.save(servicoEntity);
    }

    @Override
    public void deletarServico(Long id) {
        servicoRepository.deleteById(id);
    }
}
