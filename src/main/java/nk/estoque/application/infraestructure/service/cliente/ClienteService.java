package nk.estoque.application.infraestructure.service.cliente;

import nk.estoque.application.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.domain.cliente.Cliente;

import java.util.List;

public interface ClienteService {

    List<ClienteEntity> listaPaginada();

    ClienteEntity clientePorId(Long id);

    ClienteEntity criarCliente(Cliente cliente);

    ClienteEntity atualizarCliente(Cliente cliente, Long id);

    void excluirCliente(Long id);
}
