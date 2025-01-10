package nk.estoque.application.infraestructure.web.funcionario;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.Funcionario;

import java.math.BigDecimal;

@Data
public class FuncionarioPayload {

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal salario;

    @NotBlank(message = "O CPF é obrigatório!")
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    public Funcionario toFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setSalario(salario);
        funcionario.setCpf(cpf);
        funcionario.setTelefone(telefone);
        return funcionario;
    }
}
