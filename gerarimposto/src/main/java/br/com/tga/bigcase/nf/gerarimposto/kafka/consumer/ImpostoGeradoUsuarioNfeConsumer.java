package br.com.tga.bigcase.nf.gerarimposto.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.tga.bigcase.nf.gerarimposto.service.ImpostoGeradoUsuarioNfeService;
import br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer.GerarNfeUsuario;

@Component
public class ImpostoGeradoUsuarioNfeConsumer {
    
    @Autowired
    private ImpostoGeradoUsuarioNfeService impostoGeradoUsuarioNfeService;

    @KafkaListener(topics = "spec2-tiago-galvao-1", groupId = "tiago-g")
    public void calculaImposto(@Payload GerarNfeUsuario gerarNfeUsuario){
        impostoGeradoUsuarioNfeService.consultaImposto(gerarNfeUsuario);
    }
}