package nk.estoque.application.infraestructure.web.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.UsuarioEntity;

@Data
public class UsuarioPayLoad {

    @NotNull(message = "O nome é obrigatório!")
    private String nome;

    @NotNull(message = "O email é obrigatório!")
    private String email;

    @NotNull(message = "A senha é obrigatória!")
    private String senha;

    public UsuarioEntity toUsuario() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setName(nome);
        usuario.setEmail(email);
        usuario.setPassword(senha);
        return usuario;
    }
}
