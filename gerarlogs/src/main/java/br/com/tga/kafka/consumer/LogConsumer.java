package br.com.tga.kafka.consumer;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LogConsumer {

    @KafkaListener(topics = "spec2-tiago-galvao-2", groupId = "tiago-g")
    public void receber(@Payload String log) throws IOException {

        FileWriter fileWriter = new FileWriter("logs.txt", true);
        fileWriter.write(log);
        fileWriter.flush();
        fileWriter.close(); 
        
   }
}