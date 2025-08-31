package dev.programing.moneycontrol.exception;

public class CategoriaNotFoundException extends RuntimeException {

    public CategoriaNotFoundException() {
        super("Categoria não encontrada");
    }
}
