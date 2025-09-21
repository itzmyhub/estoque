package nk.estoque.application.servico.service;

import java.util.List;

import nk.estoque.infraestructure.web.servico.ServicoFilter;
import nk.estoque.domain.models.servico.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServicoService {

    Page<Servico> listaPaginada(Pageable pageable, ServicoFilter servicoFilter);

    List<Servico> servicosPorId(List<Long> ids);

    Servico servicoPorId(Long id);

    Servico criar(Servico servico);

    Servico atualizarServico(Long id, Servico novoServico);

    void deletarServico(Long id);
}