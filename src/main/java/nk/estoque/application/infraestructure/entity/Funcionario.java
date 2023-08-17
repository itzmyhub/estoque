package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.application.infraestructure.web.funcionario.FuncionarioPayload;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Funcionario {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private BigDecimal salario;

    private String cpf;

    private String telefone;

    public void geraFuncionario(FuncionarioPayload funcionarioPayload) {
        this.nome = funcionarioPayload.getNome();
        this.salario = funcionarioPayload.getSalario();
        this.cpf = funcionarioPayload.getCpf();
        this.telefone = funcionarioPayload.getTelefone();
    }
}
