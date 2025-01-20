package nk.estoque.application.infraestructure.entity.funcionario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nk.estoque.domain.funcionario.Funcionario;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private BigDecimal salario;

    private String cpf;

    private String telefone;

    public static FuncionarioEntity fromFuncionario(Funcionario funcionario) {
        return FuncionarioEntity.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .salario(funcionario.getSalario())
                .cpf(funcionario.getCpf())
                .telefone(funcionario.getTelefone())
                .build();
    }

    public static List<FuncionarioEntity> fromFuncionario(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioEntity::fromFuncionario)
                .collect(Collectors.toList());
    }

}
