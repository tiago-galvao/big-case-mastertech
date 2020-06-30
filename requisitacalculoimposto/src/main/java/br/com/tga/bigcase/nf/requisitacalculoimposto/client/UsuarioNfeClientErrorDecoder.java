package br.com.tga.bigcase.nf.requisitacalculoimposto.client;

import feign.codec.ErrorDecoder;
import br.com.tga.bigcase.nf.requisitacalculoimposto.exception.DadosNotFoundException;
import feign.Response;


public class UsuarioNfeClientErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 404) {
            return new DadosNotFoundException();
        }else{
            return errorDecoder.decode(s, response);
        }
    }


}