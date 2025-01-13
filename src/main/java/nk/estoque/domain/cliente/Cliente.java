package nk.estoque.domain.cliente;

import lombok.Data;

@Data
public class Cliente {
    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String email;
}
