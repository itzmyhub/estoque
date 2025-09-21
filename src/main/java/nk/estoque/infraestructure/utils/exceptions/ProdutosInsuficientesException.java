package nk.estoque.infraestructure.utils.exceptions;

public class ProdutosInsuficientesException extends RuntimeException{
    public ProdutosInsuficientesException(String s) {
        super(s);
    }
}
