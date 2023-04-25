package kafkademo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer
{
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    String kafkaTopic = "java_in_use_topic";
    public void send(Object message)
    {
        kafkaTemplate.send(kafkaTopic, message);
    }
}
