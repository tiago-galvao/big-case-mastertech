package br.com.tga.bigcase.nf.gerarimposto.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EnviaNfeProducer {
    
    @Autowired
    private KafkaTemplate<String, NotaGeradaNfeProducer> producer;

    public void enviarAoKafka(NotaGeradaNfeProducer notaGeradaNfeProducer) {
        producer.send("spec2-tiago-galvao-2", notaGeradaNfeProducer);
    }
}