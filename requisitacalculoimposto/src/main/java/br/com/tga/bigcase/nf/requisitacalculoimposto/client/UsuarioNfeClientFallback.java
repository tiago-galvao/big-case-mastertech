package br.com.tga.bigcase.nf.requisitacalculoimposto.client;

public class UsuarioNfeClientFallback {
    private Exception cause;

    UsuarioNfeClientFallback(Exception cause) {
        this.cause = cause;
    }

}