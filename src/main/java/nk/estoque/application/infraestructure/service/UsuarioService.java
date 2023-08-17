package nk.estoque.application.infraestructure.service;

import nk.estoque.application.infraestructure.entity.Usuario;
import nk.estoque.application.infraestructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criaUm(Usuario usuario) {
        String generatedSecuredPasswordHash = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(12));
        usuario.setPassword(generatedSecuredPasswordHash);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> get(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }


}
