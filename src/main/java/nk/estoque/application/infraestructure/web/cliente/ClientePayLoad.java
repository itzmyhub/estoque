package nk.estoque.application.infraestructure.web.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.Cliente;

@Data
public class ClientePayLoad {

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório!")
    private String cpf;

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @NotNull(message = "O número do serviço é obrigatório")
    private Long id_servico;

    public Cliente toCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setId_servico(id_servico);
        return cliente;
    }
}
