package nk.estoque.application.infraestructure.utils.exceptions;

public class ProdutosInsuficientesException extends RuntimeException{
    public ProdutosInsuficientesException(String s) {
        super(s);
    }
}
