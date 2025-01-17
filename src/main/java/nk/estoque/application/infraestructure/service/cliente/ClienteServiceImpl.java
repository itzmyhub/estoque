package nk.estoque.application.infraestructure.service.cliente;

import nk.estoque.application.infraestructure.entity.cliente.ClienteEntity;
import nk.estoque.application.infraestructure.utils.exceptions.IdNaoEncontradoException;
import nk.estoque.application.infraestructure.persistence.repository.ClienteRepository;
import nk.estoque.domain.cliente.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteEntity> listaPaginada() {
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity clientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("ID número " + id + "não encontrado!"));
    }

    @Override
    public ClienteEntity criarCliente(Cliente cliente) {
        ClienteEntity clienteEntity = ClienteEntity.fromCliente(cliente);
        return clienteRepository.save(clienteEntity);
    }

    @Override
    public ClienteEntity atualizarCliente(Cliente cliente, Long id) {
        ClienteEntity clienteEntity = clienteRepository.findById(id)
                .orElseThrow(() -> new IdNaoEncontradoException("Cliente com ID " + id + " não encontrado"));

        //TODO IMPLEMENTAR A ATUALIZAÇÃO DO CLIENTE

        return clienteRepository.save(clienteEntity);
    }

    @Override
    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
