package nk.estoque.application.cliente.service;

import nk.estoque.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.domain.models.cliente.Cliente;

import java.util.List;

public interface ClienteService {

    List<ClienteEntity> listaPaginada();

    ClienteEntity clientePorId(Long id);

    ClienteEntity criarCliente(Cliente cliente);

    ClienteEntity atualizarCliente(Cliente cliente, Long id);

    void excluirCliente(Long id);
}
