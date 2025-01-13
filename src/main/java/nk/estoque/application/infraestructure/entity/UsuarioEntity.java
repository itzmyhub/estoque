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
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column
    private String email;

    @NotNull
    @Column
    private String name;

    @NotNull
    @Column
    private String password;

    @Column
    private Timestamp createdAt;

    public User toUserDetails() {
        return new User(email, password, List.of());
    }
}
