package pl.bugdemons.utils.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;

import static pl.bugdemons.utils.kafka.config.KafkaConfig.getProducerProperties;

@Slf4j
public class CustomKafkaProducer implements AutoCloseable {

    private final KafkaProducer<String, SpecificRecordBase> producer;
    private final String topic;

    //todo add schema id to serialize data
    protected CustomKafkaProducer(String topic) {
        this.topic = topic;
        this.producer = new KafkaProducer<String, SpecificRecordBase>(getProducerProperties());
    }

    protected Headers createHeaders() {
        //if needed add required headers here
        return new RecordHeaders();
    }

    protected void sendEvent(SpecificRecordBase avroRecord, String key) {
        var headers = createHeaders();
        var producerRecord = new ProducerRecord<>(topic, 1, key, avroRecord, headers);
        try {
            log.info("Sending event to kafka topic: [{}], with key: [{}]", topic, key);
            producer.send(producerRecord);
        } catch (Exception e) {
            log.error("Error while sending event to kafka topic: {}", e.getCause().getMessage());
        } finally {
            producer.flush();
        }
    }

    @Override
    public void close() {
        producer.close();
    }
}
