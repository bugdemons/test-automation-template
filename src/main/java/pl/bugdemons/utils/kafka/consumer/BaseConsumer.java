package pl.bugdemons.utils.kafka.consumer;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import lombok.SneakyThrows;

import pl.bugdemons.utils.kafka.exception.EventNotFoundException;

public abstract class BaseConsumer {

    @SneakyThrows
    protected <T> T getEvent(String topic, Class<T> clazz, int limitOfPolls, Predicate<T> predicate) {
        int nLimitOfPolls = limitOfPolls;

        try (CustomKafkaConsumer consumer = new CustomKafkaConsumer(topic)) {
            do {
                nLimitOfPolls--;

                var events = consumer.pollEvents(clazz);
                var event = findEvent(events, predicate);
                if (Objects.nonNull(event)) {
                    return event;
                }
            } while (nLimitOfPolls > 0);
        }
        throw new EventNotFoundException();
    }

    protected <T> T getEvent(String topicName, Class<T> clazz, Predicate<T> predicate) {
        return getEvent(topicName, clazz, 50, predicate);
    }

    private <T> T findEvent(List<T> events, Predicate<T> predicate) {
        return events.stream()
                .filter(predicate)
                .reduce((first, last) -> last)
                .orElse(null);
    }
}
