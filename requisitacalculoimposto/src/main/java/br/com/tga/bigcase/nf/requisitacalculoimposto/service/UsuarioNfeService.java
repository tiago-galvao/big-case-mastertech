package br.com.tga.bigcase.nf.requisitacalculoimposto.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tga.bigcase.nf.gerarimposto.producer.NotaGeradaNfeProducer;
import br.com.tga.bigcase.nf.requisitacalculoimposto.exception.DadosNotFoundException;
import br.com.tga.bigcase.nf.requisitacalculoimposto.exception.DocumentoInvalidoException;
import br.com.tga.bigcase.nf.requisitacalculoimposto.helper.IsCnpj;
import br.com.tga.bigcase.nf.requisitacalculoimposto.helper.IsCpf;
import br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer.GerarNfeUsuario;
import br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer.LogProducer;
import br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer.UsuarioNfeProducer;
import br.com.tga.bigcase.nf.requisitacalculoimposto.model.Nfe;
import br.com.tga.bigcase.nf.requisitacalculoimposto.model.UsuarioNfe;
import br.com.tga.bigcase.nf.requisitacalculoimposto.model.create.CreateNfeRequest;
import br.com.tga.bigcase.nf.requisitacalculoimposto.repository.UsuarioNfeRepository;

@Service
public class UsuarioNfeService {

    @Autowired
    private UsuarioNfeRepository usuarioNfeRepository;

    @Autowired
    private UsuarioNfeProducer usuarioNfeProducer;

    @Autowired
    private LogProducer logProducer;

    public void validaDocumento(String documento) {
        if (!IsCpf.isCPF(documento) && !IsCnpj.isCNPJ(documento)) {
            throw new DocumentoInvalidoException();
        }
    }

    @Transactional
    public UsuarioNfe criar(CreateNfeRequest createNfeRequest) {
        validaDocumento(createNfeRequest.getDocumento());
        UsuarioNfe usuarioNfe = new UsuarioNfe();
        usuarioNfe.setDocumento(createNfeRequest.getDocumento());
        usuarioNfe.setValor(createNfeRequest.getValor());
        usuarioNfe.setStatus("pending");
        usuarioNfe.setNfe(null);
        usuarioNfe = usuarioNfeRepository.save(usuarioNfe);

        GerarNfeUsuario gerarNfeUsuario = new GerarNfeUsuario();
        gerarNfeUsuario.setValor(usuarioNfe.getValor());
        gerarNfeUsuario.setDocumento(usuarioNfe.getDocumento());

        geraLogs(Boolean.FALSE, createNfeRequest.getDocumento(), createNfeRequest.getValor());
        usuarioNfeProducer.enviarAoKafka(gerarNfeUsuario);

        return usuarioNfe;
    }

    public List<UsuarioNfe> buscar(String documento) {
        validaDocumento(documento);
        List<UsuarioNfe> infosNfe = usuarioNfeRepository.findAllByDocumento(documento);
        if (infosNfe.isEmpty()) {
            throw new DadosNotFoundException();
        }
        geraLogs(Boolean.FALSE, documento, BigDecimal.valueOf(0L));
        return infosNfe;
    }

    @Transactional
    public void atualizarNfe(NotaGeradaNfeProducer notaGeradaNfeProducer) {
        UsuarioNfe usuarioNfe = new UsuarioNfe();
        usuarioNfe.setDocumento(notaGeradaNfeProducer.getDocumento());
        usuarioNfe.setValor(notaGeradaNfeProducer.getValorInicial());
        usuarioNfe.setStatus("complete");

        Nfe nfe = new Nfe();
        nfe.setValorInicial(notaGeradaNfeProducer.getValorInicial());
        nfe.setValorCSLL(notaGeradaNfeProducer.getValorCSLL());
        nfe.setValorCofins(notaGeradaNfeProducer.getValorCofins());
        nfe.setValorIRRF(notaGeradaNfeProducer.getValorIRRF());
        nfe.setValorFinal(notaGeradaNfeProducer.getValorFinal());

        usuarioNfe.setNfe(nfe);
        usuarioNfe = usuarioNfeRepository.save(usuarioNfe);

        GerarNfeUsuario gerarNfeUsuario = new GerarNfeUsuario();
        gerarNfeUsuario.setValor(usuarioNfe.getValor());
        gerarNfeUsuario.setDocumento(usuarioNfe.getDocumento());

        geraLogs(Boolean.FALSE, notaGeradaNfeProducer.getDocumento(), notaGeradaNfeProducer.getValorInicial());
        usuarioNfeProducer.enviarAoKafka(gerarNfeUsuario);
    }

    public void geraLogs(Boolean emissa, String documento, BigDecimal valor){
        String log = "";
        String result = "";
        if(Boolean.TRUE.equals(emissa)){
            log = "[%s] [Emissão]: %s acaba de pedir a emissão de uma NF no valor de R$ %s!";
            result = String.format(log, dataHoraLog(), documento, valor);
            logProducer.enviarLogAoKafka(result);
        }else{
            log = "[%s] [Consulta]: %s acaba de pedir os dados das suas notas fiscais.";
            result = String.format(log, dataHoraLog(), documento);
            logProducer.enviarLogAoKafka(result);
        }   
    }

    public String dataHoraLog(){
        Calendar cal = Calendar.getInstance();
        Timestamp dataHora = new Timestamp(cal.getTimeInMillis());
        return dataHora.toString();
    }

    
}