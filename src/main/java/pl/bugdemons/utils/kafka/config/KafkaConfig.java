package pl.bugdemons.utils.kafka.config;

import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import static org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;
import static pl.bugdemons.utils.config.ConfigurationManager.getKafkaProperties;

public class KafkaConfig {

    private static final Map<String, String> KAFKA_PROPERTIES = getKafkaProperties();
    private static final String KAFKA_PREFIX = "kafka.";


    //todo add deserializer for specified avro (value)
    public static Properties getConsumerProperties() {
        var properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, getProperty(BOOTSTRAP_SERVERS_CONFIG));
        properties.put(GROUP_ID_CONFIG, getProperty(GROUP_ID_CONFIG));
        properties.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        return properties;
    }

    //todo add serializer for specified avro (value)
    public static Properties getProducerProperties() {
        var properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, getProperty(BOOTSTRAP_SERVERS_CONFIG));
        properties.put(CLIENT_ID_CONFIG, getProperty(CLIENT_ID_CONFIG));
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

    private static String getProperty(String propName) {
        return KAFKA_PROPERTIES.get("kafka." + propName);
    }
}
