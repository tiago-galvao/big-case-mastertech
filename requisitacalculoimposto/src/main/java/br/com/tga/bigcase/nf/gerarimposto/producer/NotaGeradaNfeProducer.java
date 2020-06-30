package br.com.tga.bigcase.nf.gerarimposto.producer;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

public class NotaGeradaNfeProducer {

    @Getter @Setter private String documento;
    @Getter @Setter private BigDecimal valorInicial;
    @Getter @Setter private BigDecimal valorIRRF;
    @Getter @Setter private BigDecimal valorCSLL;
    @Getter @Setter private BigDecimal valorCofins;
    @Getter @Setter private BigDecimal valorFinal;
    
}