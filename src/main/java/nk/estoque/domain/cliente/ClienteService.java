package nk.estoque.domain.cliente;

import nk.estoque.application.infraestructure.entity.ClienteEntity;

import java.util.List;

public interface ClienteService {

    List<ClienteEntity> listaPaginada();

    ClienteEntity clientePorId(Long id);

    ClienteEntity criarCliente(Cliente cliente);

    ClienteEntity atualizarCliente(Cliente cliente, Long id);

    void excluirCliente(Long id);
}
