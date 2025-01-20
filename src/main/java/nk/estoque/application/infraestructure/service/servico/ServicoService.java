package nk.estoque.application.infraestructure.service.servico;

import java.util.List;
import nk.estoque.application.infraestructure.entity.servico.ServicoEntity;
import nk.estoque.domain.servico.Servico;

public interface ServicoService {

    List<ServicoEntity> listaPaginada();

    List<ServicoEntity> servicosPorId(List<Long> ids);

    ServicoEntity servicoPorId(Long id);

    ServicoEntity criar(Servico servico);

    ServicoEntity atualizarServico(Long id, Servico novoServico);

    void deletarServico(Long id);
}
