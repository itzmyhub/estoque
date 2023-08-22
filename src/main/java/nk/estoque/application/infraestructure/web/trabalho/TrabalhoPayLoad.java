package nk.estoque.application.infraestructure.web.trabalho;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nk.estoque.application.infraestructure.entity.Trabalho;

import java.math.BigDecimal;

@Data
public class TrabalhoPayLoad {

    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.00", message = "O valor deve ser maior ou igual a zero!")
    private BigDecimal maoDeObra;

    public Trabalho toTrabalho() {
        Trabalho trabalho = new Trabalho();
        trabalho.setMaoDeObra(maoDeObra);
        return trabalho;
    }
}
