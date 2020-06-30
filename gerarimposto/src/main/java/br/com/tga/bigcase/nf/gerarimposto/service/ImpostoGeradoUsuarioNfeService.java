package br.com.tga.bigcase.nf.gerarimposto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tga.bigcase.nf.gerarimposto.client.GetInfoReceitoFederal;
import br.com.tga.bigcase.nf.gerarimposto.client.ReceitaFederal;
import br.com.tga.bigcase.nf.gerarimposto.exception.DocumentoInvalidoException;
import br.com.tga.bigcase.nf.gerarimposto.helper.CalculaImposto;
import br.com.tga.bigcase.nf.gerarimposto.helper.IsCnpj;
import br.com.tga.bigcase.nf.gerarimposto.helper.IsCpf;
import br.com.tga.bigcase.nf.gerarimposto.kafka.producer.EnviaNfeProducer;
import br.com.tga.bigcase.nf.gerarimposto.kafka.producer.NotaGeradaNfeProducer;
import br.com.tga.bigcase.nf.gerarimposto.model.ImpostoGeradoUsuarioNfe;
import br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer.GerarNfeUsuario;

@Service
public class ImpostoGeradoUsuarioNfeService {
    
    @Autowired
    private ReceitaFederal receitaFederal;

    @Autowired
    private EnviaNfeProducer enviaNfeProducer;

    public void consultaImposto(GerarNfeUsuario gerarNfeUsuario){
        if(IsCpf.isCPF(gerarNfeUsuario.getDocumento())){
            enviaNota(new NotaGeradaNfeProducer(geraImpostoPf(gerarNfeUsuario)));
        }else if(IsCnpj.isCNPJ(gerarNfeUsuario.getDocumento())){
            enviaNota(new NotaGeradaNfeProducer(geraImpostoPj(gerarNfeUsuario)));
        }else{
            throw new DocumentoInvalidoException();
        }
    }

    public void enviaNota(NotaGeradaNfeProducer notaGeradaNfeProducer){
        enviaNfeProducer.enviarAoKafka(notaGeradaNfeProducer);
    }

    public ImpostoGeradoUsuarioNfe geraImpostoPj(GerarNfeUsuario gerarNfeUsuario){
        CalculaImposto calculaImposto = new CalculaImposto();
        GetInfoReceitoFederal valorCapital = receitaFederal.getCapital(gerarNfeUsuario.getDocumento());
        if(Double.parseDouble(valorCapital.getCapital()) > 1000000){
            return calculaImposto.impostoPessoaJuridicaSimplesNacional(gerarNfeUsuario.getDocumento(), gerarNfeUsuario.getValor());
        }else {
            return calculaImposto.impostoPessoaJuridicaSimplesNacional(gerarNfeUsuario.getDocumento(), gerarNfeUsuario.getValor());
        }
    }

    public ImpostoGeradoUsuarioNfe geraImpostoPf(GerarNfeUsuario gerarNfeUsuario){
        CalculaImposto calculaImposto = new CalculaImposto();
        return calculaImposto.impostoPessoaFisica(gerarNfeUsuario.getDocumento(), gerarNfeUsuario.getValor());
    }
}