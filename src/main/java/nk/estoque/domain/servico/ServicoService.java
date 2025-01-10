package nk.estoque.domain.servico;

import java.util.List;
import nk.estoque.application.infraestructure.entity.Servico;

public interface ServicoService {

    List<Servico> listaPaginada();

    Servico criar(Servico servico);

    Servico atualizarServico(Long id, Servico novoServico);

    void deletarServico(Long id);
}
