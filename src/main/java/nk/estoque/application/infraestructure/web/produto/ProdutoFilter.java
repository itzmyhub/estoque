package nk.estoque.application.infraestructure.web.produto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;
import lombok.Setter;

import java.util.Optional;

@Setter
public class ProdutoFilter {
    @Nullable
    @Size(min = 0, max = 50)
    private String nome;

    public Optional<String> getNome() {
        return Optional.ofNullable(nome);
    }
}
