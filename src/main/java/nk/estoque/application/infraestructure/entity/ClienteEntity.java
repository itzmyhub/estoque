package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.cliente.Cliente;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String telefone;

    private String email;

    private String cpf;

    public static ClienteEntity fromCliente(Cliente cliente) {
        return ClienteEntity.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .telefone(cliente.getTelefone())
                .build();
    }

    public static List<ClienteEntity> fromClientes(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteEntity::fromCliente)
                .collect(Collectors.toList());
    }
}
