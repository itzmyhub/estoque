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
import java.security.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements Serializable {
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
}
