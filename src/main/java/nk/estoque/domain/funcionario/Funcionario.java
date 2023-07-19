package nk.estoque.domain.funcionario;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Funcionario {

    private Long id;

    private String nome;

    private BigDecimal salario;

    private String cpf;

    private String telefone;
}
