package br.com.tga.bigcase.nf.requisitacalculoimposto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Dados não encontrados")
public class DadosNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}