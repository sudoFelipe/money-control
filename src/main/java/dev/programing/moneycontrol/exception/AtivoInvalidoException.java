package dev.programing.moneycontrol.exception;

public class AtivoInvalidoException extends RuntimeException {

    public AtivoInvalidoException () {
        super("Dados inválidos ao recuperar informações do ativo.");
    }
}
