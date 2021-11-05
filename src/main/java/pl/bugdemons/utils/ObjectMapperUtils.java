package pl.bugdemons.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class ObjectMapperUtils {

    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                .enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public static String prettyPrint(Object object) {
        try {
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter()
                    .withObjectIndenter(new DefaultIndenter(" ", DefaultIndenter.SYS_LF));

            return createObjectMapper()
                    .setDefaultPrettyPrinter(printer)
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error while processing JSON: {}", e.getMessage());
        }
        return "Could not serialize given object: " + object.toString();
    }

    public static Map<String, Object> convertToNonNullValueMap(Object object) {
        var mapper = createObjectMapper();
        Map<String, Object> result = mapper.convertValue(object, HashMap.class);

        return result.entrySet()
                .stream()
                .filter(entry -> entry.getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
