package br.com.tga.bigcase.nf.requisitacalculoimposto.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.tga.bigcase.nf.gerarimposto.producer.NotaGeradaNfeProducer;
import br.com.tga.bigcase.nf.requisitacalculoimposto.service.UsuarioNfeService;

@Service
public class ImpostoGeradoNfeConsumer {
    @Autowired
    private UsuarioNfeService usuarioNfeService;

    @KafkaListener(topics = "spec2-tiago-galvao-2", groupId = "tiago-g")
    public void calculaImposto(@Payload NotaGeradaNfeProducer notaGeradaNfeProducer){
        usuarioNfeService.atualizarNfe(notaGeradaNfeProducer);
    }
}