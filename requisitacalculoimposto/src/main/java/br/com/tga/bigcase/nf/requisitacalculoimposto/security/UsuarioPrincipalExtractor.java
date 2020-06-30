package br.com.tga.bigcase.nf.requisitacalculoimposto.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import java.util.Map;

public class UsuarioPrincipalExtractor implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        Usuario usuario = new Usuario();
        usuario.setId((Integer) map.get("id"));
        usuario.setName((String) map.get("name"));
        return usuario;
    }

}
