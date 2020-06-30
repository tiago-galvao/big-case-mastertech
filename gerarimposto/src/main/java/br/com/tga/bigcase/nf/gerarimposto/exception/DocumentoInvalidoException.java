package br.com.tga.bigcase.nf.gerarimposto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Documento informado é invalido")
public class DocumentoInvalidoException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}