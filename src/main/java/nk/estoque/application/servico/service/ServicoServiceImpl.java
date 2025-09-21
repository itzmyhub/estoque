package nk.estoque.application.servico.service;
import jakarta.persistence.EntityNotFoundException;
import nk.estoque.application.servico.useCase.SalvarServicoUseCase;
import nk.estoque.domain.repositories.ServicoRepository;
import nk.estoque.infraestructure.web.servico.ServicoFilter;
import nk.estoque.domain.models.servico.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository servicoRepository;
    private final SalvarServicoUseCase salvarServicoUseCase;

    public ServicoServiceImpl(ServicoRepository servicoRepository,
                              SalvarServicoUseCase salvarServicoUseCase) {
        this.servicoRepository = servicoRepository;
        this.salvarServicoUseCase = salvarServicoUseCase;
    }

    @Override
    public Page<Servico> listaPaginada(Pageable pageable, ServicoFilter filter) {
        if (filter.getNome().isEmpty() || filter.getNome().get().isBlank()) {
            return servicoRepository.findAll(pageable);
        }
        return servicoRepository.findByNomeContainingIgnoreCase(filter.getNome().get(), pageable);
    }

    @Override
    public List<Servico> servicosPorId(List<Long> ids) {
        return ids.stream()
                .map(servicoRepository::servicoPorId)
                .toList();
    }

    @Override
    public Servico servicoPorId(Long id) {
        return servicoRepository.servicoPorId(id);
    }

    @Override
    public Servico criar(Servico servico) {
        return salvarServicoUseCase.executarCriacao(servico);
    }

    @Override
    public Servico atualizarServico(Long id, Servico novoServico) {
        Servico servicoAntigo = servicoRepository.servicoPorId(id);
        if (servicoAntigo == null) {
            throw new EntityNotFoundException("Serviço não encontrado com id " + id);
        }
        return salvarServicoUseCase.executarAtualizacao(servicoAntigo, novoServico);
    }

    @Override
    public void deletarServico(Long id) {
        servicoRepository.deleteById(id);
    }
}

