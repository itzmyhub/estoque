package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Servico;
import nk.estoque.application.infraestructure.exceptions.IdNaoEncontradoException;
import nk.estoque.application.infraestructure.repository.ServicoRepository;
import nk.estoque.domain.servico.ServicoService;

import java.util.List;

public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoServiceImpl(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Override
    public List<Servico> listaPaginada() {
        return servicoRepository.findAll();
    }

    @Override
    public Servico criar(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public Servico atualizarServico(Long id, Servico novoServico) {
        Servico servicoEncontrado = servicoRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Produto com ID " + id + " não encontrado"));

        servicoEncontrado.setMaoDeObra(novoServico.getMaoDeObra());

        return servicoRepository.save(servicoEncontrado);
    }

    @Override
    public void deletarServico(Long id) {
        servicoRepository.deleteById(id);
    }
}
