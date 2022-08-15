package pl.bugdemons.utils.kafka.producer;

import org.apache.avro.specific.SpecificRecordBase;

public abstract class BaseProducer {

    protected <T extends SpecificRecordBase> void sendMessage(T event, String topic, String key) {
        try (var producer = new CustomKafkaProducer(topic)) {
            producer.sendEvent(event, key);
        }
    }
}
