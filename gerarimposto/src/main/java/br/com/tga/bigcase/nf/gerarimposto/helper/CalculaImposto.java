package br.com.tga.bigcase.nf.gerarimposto.helper;

import java.math.BigDecimal;

import br.com.tga.bigcase.nf.gerarimposto.model.ImpostoGeradoUsuarioNfe;

public class CalculaImposto {
    /*
        -- Para emitir NF Pessoa Jurídica optante pelo simples nacional. 
        Será descontado somente o IRRF (1,5%) R$ 15,00, valor da NF líquido 
        será R$ 985,00.
        
        --Para Emitir NF Pessoa Jurídica não optante pelo simples nacional. 
        Será  descontado o IRRF (1,5%) , CSLL (3%) , PIS /Cofins (0,65%). 
        Seria R$ 948,50 valor líquido.
    */

    private static final BigDecimal VALOR_IRRF = BigDecimal.valueOf(0.015);
    private static final BigDecimal VALOR_CSLL = BigDecimal.valueOf(0.03);
    private static final BigDecimal VALOR_PIS_CONFINS = BigDecimal.valueOf(0.0065);

    public BigDecimal calculaIRRF(BigDecimal valor){
        return (valor.multiply(VALOR_IRRF));
    }

    public BigDecimal calculaCSLL(BigDecimal valor){
        return (valor.multiply(VALOR_CSLL));
    }

    public BigDecimal calculaPISConfins(BigDecimal valor){
        return (valor.multiply(VALOR_PIS_CONFINS));
    }

    public ImpostoGeradoUsuarioNfe impostoPessoaFisica(String documento, BigDecimal valor){
        return new ImpostoGeradoUsuarioNfe(documento, valor, BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), valor);
    }

    public ImpostoGeradoUsuarioNfe impostoPessoaJuridicaComplesNacional(String documento, BigDecimal valor){
        BigDecimal valorFinal = valor.subtract(calculaIRRF(valor));
        return new ImpostoGeradoUsuarioNfe(documento, valor, calculaIRRF(valor), BigDecimal.valueOf(0.0), BigDecimal.valueOf(0.0), valorFinal);
    }

    public ImpostoGeradoUsuarioNfe impostoPessoaJuridicaSimplesNacional(String documento, BigDecimal valor){
        BigDecimal valorFinal = valor.subtract(calculaIRRF(valor));
        valorFinal = valorFinal.subtract(calculaCSLL(valor));
        valorFinal = valorFinal.subtract(calculaPISConfins(valor));
        return new ImpostoGeradoUsuarioNfe(documento, valor, calculaIRRF(valor), calculaCSLL(valor), calculaPISConfins(valor), valorFinal);
    }
}