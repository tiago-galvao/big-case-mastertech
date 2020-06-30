package br.com.tga.bigcase.nf.requisitacalculoimposto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.tga.bigcase.nf.requisitacalculoimposto.model.UsuarioNfe;
import br.com.tga.bigcase.nf.requisitacalculoimposto.model.create.CreateNfeRequest;
import br.com.tga.bigcase.nf.requisitacalculoimposto.model.create.CreateNfeResponse;
import br.com.tga.bigcase.nf.requisitacalculoimposto.security.Usuario;
import br.com.tga.bigcase.nf.requisitacalculoimposto.service.UsuarioNfeService;

@RestController
@RequestMapping("/notafiscal")
public class UsuarioNfeController {
    
    @Autowired
    private UsuarioNfeService usuarioNfeService;

    @PostMapping("/gerar")
    @ResponseStatus(code= HttpStatus.CREATED)
    public CreateNfeResponse emitirNotaFiscal( @RequestBody @Valid CreateNfeRequest createNfeRequest, @AuthenticationPrincipal Usuario usuario){
        UsuarioNfe usuarioNfe = usuarioNfeService.criar(createNfeRequest);
        CreateNfeResponse createNfeResponse = new CreateNfeResponse();
        createNfeResponse.setStatus(usuarioNfe.getStatus());
        return createNfeResponse;
    }

    @GetMapping("/consultar/{documento}")
    public List<UsuarioNfe> consultarNotasFiscais(@PathVariable String documento, @AuthenticationPrincipal Usuario usuario){
        List<UsuarioNfe> lista = null;
        lista = usuarioNfeService.buscar(documento);
        return lista;
    }

}