package nk.estoque.application.infraestructure.web.cliente;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import nk.estoque.domain.cliente.Cliente;

@Data
public class ClientePayload {

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
