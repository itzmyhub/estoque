package nk.estoque.domain.model.cliente;

import nk.estoque.application.infraestructure.entity.Cliente;

import java.util.List;

public interface TodosClientes {

    List<Cliente> listaPaginada();

    Cliente novoCliente(Cliente cliente);

    Cliente atualizarCliente(Long id, Cliente cliente);

    void excluirCliente(Long id);
}
