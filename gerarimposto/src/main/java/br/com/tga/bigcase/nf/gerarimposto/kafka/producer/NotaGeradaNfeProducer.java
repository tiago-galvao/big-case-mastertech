package br.com.tga.bigcase.nf.gerarimposto.kafka.producer;

import java.math.BigDecimal;

import br.com.tga.bigcase.nf.gerarimposto.model.ImpostoGeradoUsuarioNfe;
import lombok.Getter;
import lombok.Setter;

public class NotaGeradaNfeProducer {

    @Getter @Setter private String documento;
    @Getter @Setter private BigDecimal valorInicial;
    @Getter @Setter private BigDecimal valorIRRF;
    @Getter @Setter private BigDecimal valorCSLL;
    @Getter @Setter private BigDecimal valorCofins;
    @Getter @Setter private BigDecimal valorFinal;

    public NotaGeradaNfeProducer(){}

    public NotaGeradaNfeProducer(ImpostoGeradoUsuarioNfe impostoGeradoUsuarioNfe){
        this.documento = impostoGeradoUsuarioNfe.getDocumento();
        this.valorInicial = impostoGeradoUsuarioNfe.getValorInicial();
        this.valorIRRF = impostoGeradoUsuarioNfe.getValorIRRF();
        this.valorCSLL = impostoGeradoUsuarioNfe.getValorCSLL();
        this.valorCofins = impostoGeradoUsuarioNfe.getValorCofins();
        this.valorFinal = impostoGeradoUsuarioNfe.getValorFinal();
    }

}