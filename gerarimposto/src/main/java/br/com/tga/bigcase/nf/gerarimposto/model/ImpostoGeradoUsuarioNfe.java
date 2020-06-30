package br.com.tga.bigcase.nf.gerarimposto.model;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

public class ImpostoGeradoUsuarioNfe {

    @Getter @Setter private String documento;
    @Getter @Setter private BigDecimal valorInicial;
    @Getter @Setter private BigDecimal valorIRRF;
    @Getter @Setter private BigDecimal valorCSLL;
    @Getter @Setter private BigDecimal valorCofins;
    @Getter @Setter private BigDecimal valorFinal;

    public ImpostoGeradoUsuarioNfe(){}

    public ImpostoGeradoUsuarioNfe(String documento, BigDecimal valorInicial, BigDecimal valorIRRF, BigDecimal valorCSLL, BigDecimal valorCofins, BigDecimal valorFinal){
        this.documento = documento;
        this.valorInicial = valorInicial;
        this.valorIRRF = valorIRRF;
        this.valorCSLL = valorCSLL;
        this.valorCofins = valorCofins;
        this.valorFinal = valorFinal;
    }
}