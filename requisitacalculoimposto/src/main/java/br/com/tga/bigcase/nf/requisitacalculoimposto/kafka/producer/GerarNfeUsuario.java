package br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class GerarNfeUsuario {
    @Getter @Setter private String documento;
    @Getter @Setter private BigDecimal valor;
}