package nk.estoque.application.infraestructure.entity.jwt;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {
    @NotNull(message = "O email é obrigatório!")
    private String email;
    @NotNull(message = "A senha é obrigatória!")
    private String senha;
}
