package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.ServicoEntity;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.application.infraestructure.repository.ServicoRepository;
import nk.estoque.domain.produto.ProdutosService;
import nk.estoque.domain.servico.Servico;
import nk.estoque.domain.servico.ServicoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository servicoRepository;

    private final ProdutosService produtosService;

    public ServicoServiceImpl(ServicoRepository servicoRepository, ProdutosService produtosService) {
        this.servicoRepository = servicoRepository;
        this.produtosService = produtosService;
    }

    @Override
    public List<ServicoEntity> listaPaginada() {
        return servicoRepository.findAll();
    }

    @Override
    public List<ServicoEntity> servicosPorId(List<Long> ids) {
        List<ServicoEntity> servicos = servicoRepository.findAllById(ids);

        if (servicos.size() != ids.size()) {
            List<Long> idsNaoEncontrados = new ArrayList<>(ids);
            servicos.forEach(servico -> idsNaoEncontrados.remove(servico.getId()));

            throw new IdNaoEncontradoException("Serviços com os IDs não encontrados: " + idsNaoEncontrados);
        }

        return servicos;
    }

    @Override
    public ServicoEntity criar(Servico servico) {
        ServicoEntity servicoEntity = ServicoEntity.fromServico(servico);
        servicoEntity.setProdutos(produtosService.produtosPorId(servico.getProdutos()));
        return servicoRepository.save(servicoEntity);
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
