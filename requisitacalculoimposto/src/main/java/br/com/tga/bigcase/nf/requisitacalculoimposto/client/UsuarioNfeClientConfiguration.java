package br.com.tga.bigcase.nf.requisitacalculoimposto.client;

import feign.Feign;
import feign.codec.ErrorDecoder;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.springframework.context.annotation.Bean;

public class UsuarioNfeClientConfiguration {
    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new UsuarioNfeClientErrorDecoder();
    }

    @Bean
    public Feign.Builder builder() {
        FeignDecorators decorators = FeignDecorators.builder()
                .withFallbackFactory(UsuarioNfeClientFallback::new)
                .build();

        return Resilience4jFeign.builder(decorators);
    }

    
}