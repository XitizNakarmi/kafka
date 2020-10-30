package com.example.kafkademo.kafkademo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@SpringBootApplication
public class KafkademoApplication {

    public static void main(String[] args) {

        SpringApplication.run(KafkademoApplication.class, args);

        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer kafkaProducer = new KafkaProducer(props);

        try {
            for (int i=0; i<100; i++){
                kafkaProducer.send(new ProducerRecord("my_topic", "Message "+Integer.toString(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }

}
