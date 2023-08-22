package nk.estoque.application.infraestructure.persistence;

import nk.estoque.application.infraestructure.entity.Cliente;
import nk.estoque.application.infraestructure.entity.Funcionario;
import nk.estoque.application.infraestructure.repository.ClienteRepository;
import nk.estoque.domain.model.cliente.TodosClientes;

import java.util.List;
import java.util.Optional;

public class TodosClientesAdapter implements TodosClientes {

    private final ClienteRepository clienteRepository;

    public TodosClientesAdapter(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> listaPaginada() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente novoCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(id);
        if (clienteEncontrado.isEmpty()) {
            throw new RuntimeException("implementar melhor dps");
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    @Override
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
