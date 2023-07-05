package NK.estoque.model;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
public class Product {

    @GeneratedValue
    private Long id;

    @NonNull
    private String nome;

    @NonNull
    private BigDecimal valor;

    @NonNull
    private int qtd_items;

    @NonNull
    private int numero_produto;

    @NonNull
    private boolean em_estoque;
}
