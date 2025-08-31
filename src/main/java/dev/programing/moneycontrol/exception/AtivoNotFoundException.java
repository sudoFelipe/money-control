package dev.programing.moneycontrol.exception;

public class AtivoNotFoundException extends RuntimeException {

    public AtivoNotFoundException() {
        super("Ativo não encontrado.");
    }
}
