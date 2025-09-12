package nk.estoque.application.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import nk.estoque.domain.models.cliente.Cliente;

@Data
public class ClienteDTO {

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório!")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    private String email;

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        return cliente;
    }
}
