package nk.estoque.infraestructure.web.servico;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Setter;

import java.util.Optional;

@Setter
public class ServicoFilter {
    @Nullable
    @Size(min = 0, max = 50)
    private String nome;

    public Optional<String> getNome() {
        return Optional.ofNullable(nome);
    }
}
