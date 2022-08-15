package pl.bugdemons.utils.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import static java.util.Collections.singletonList;
import static pl.bugdemons.utils.kafka.config.KafkaConfig.getConsumerProperties;

@Slf4j
public class CustomKafkaConsumer implements AutoCloseable {

    private final KafkaConsumer<String, Object> consumer;
    private final String topic;

    protected CustomKafkaConsumer(String topic) {
        this.topic = topic;
        this.consumer = new KafkaConsumer<String, Object>(getConsumerProperties());

        consumer.subscribe(singletonList(topic));
    }

    public <T> List<T> pollEvents(Class<T> clazz) {
        log.debug("Pooling events from topic: [{}]", topic);
        var events = new ArrayList<T>();

        try {
            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(500));
            for (var record : records) {
                log.debug("Offset: [{}]", record.offset());
                log.debug("Key:    [{}]", record.key());
                log.debug("Value:  [{}]", record.value());

                if (clazz.isInstance(record.value())) {
                    events.add(clazz.cast(record.value()));
                }
            }
            consumer.commitAsync();
        } catch (Exception e) {
            log.error("Error while pooling events from kafka: {}", e.getCause().getMessage());
        }

        log.debug("Collected [{}] events matching type [{}] from topic [{}]", events.size(), clazz.getSimpleName(), topic);

        return events;
    }

    @Override
    public void close() {
        consumer.close();
    }
}
