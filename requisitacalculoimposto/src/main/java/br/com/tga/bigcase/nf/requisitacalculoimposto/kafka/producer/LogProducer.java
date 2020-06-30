package br.com.tga.bigcase.nf.requisitacalculoimposto.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogProducer {
    
    @Autowired
    private KafkaTemplate<String, String> producer;

    public void enviarLogAoKafka(String log) {
        producer.send("spec2-tiago-galvao-2", log);
    }
}