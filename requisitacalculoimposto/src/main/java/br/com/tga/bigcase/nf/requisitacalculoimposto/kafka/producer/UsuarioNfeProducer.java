package br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UsuarioNfeProducer {
    
    @Autowired
    private KafkaTemplate<String, GerarNfeUsuario> producer;

    public void enviarAoKafka(GerarNfeUsuario gerarNfeUsuario) {
        producer.send("spec2-tiago-galvao-1", gerarNfeUsuario);
    }
}