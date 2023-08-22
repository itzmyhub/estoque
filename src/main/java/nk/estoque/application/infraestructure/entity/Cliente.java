package nk.estoque.application.infraestructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String nome;

    @NotNull
    @Column
    private String cpf;

    @NotNull
    @Column
    private String telefone;

    @NotNull
    @Column
    private Long id_servico;
}
