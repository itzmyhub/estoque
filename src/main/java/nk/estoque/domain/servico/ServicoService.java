package nk.estoque.domain.servico;

import java.util.List;
import nk.estoque.application.infraestructure.entity.ServicoEntity;

public interface ServicoService {

    List<ServicoEntity> listaPaginada();

    List<ServicoEntity> servicosPorId(List<Long> ids);

    ServicoEntity criar(Servico servico);

    ServicoEntity atualizarServico(Long id, Servico novoServico);

    void deletarServico(Long id);
}
