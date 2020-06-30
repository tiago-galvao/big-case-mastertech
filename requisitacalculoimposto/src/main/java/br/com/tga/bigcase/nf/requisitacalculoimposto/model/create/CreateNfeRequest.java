package br.com.tga.bigcase.nf.requisitacalculoimposto.model.create;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

public class CreateNfeRequest {
    
    @NotBlank @NotEmpty @NotNull
    @Getter @Setter private String documento;

    @NotNull
    @Getter @Setter private BigDecimal valor;

}