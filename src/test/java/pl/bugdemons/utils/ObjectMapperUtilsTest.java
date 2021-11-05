package pl.bugdemons.utils;

import java.util.List;

import lombok.Data;
import org.junit.jupiter.api.Test;

import pl.bugdemons.utils.junit.tag.Unit;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.bugdemons.utils.ObjectMapperUtils.convertToNonNullValueMap;
import static pl.bugdemons.utils.ObjectMapperUtils.createObjectMapper;
import static pl.bugdemons.utils.ObjectMapperUtils.prettyPrint;

@Unit
public class ObjectMapperUtilsTest {

    @Test
    public void mapperIsCreated() {
        var objectMapper = createObjectMapper();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    public void prettyPrintConvertsListToString() {
        var someObject = List.of(10, 20, 30, 40);
        var result = prettyPrint(someObject);

        assertThat(result).isNotEmpty();
        assertThat(result).contains("10", "20", "30", "40");
    }

    @Test
    public void prettyPrintConvertsObjectToString() {
        var someObject = new Address();
        var result = prettyPrint(someObject);

        assertThat(result).isNotEmpty();
        assertThat(result).contains("cityName", "Las Bugas", "zipCode", "null");
    }

    @Test
    public void convertsObjectsToNonNullValueMap() {
        var address = new Address();
        var result = convertToNonNullValueMap(address);
        assertThat(result).doesNotContainValue("zipCode");
        assertThat(result).doesNotContainKey(null);
        assertThat(result).containsEntry("cityName", "Las Bugas");

    }

    @Data
    private static class Address {

        String cityName = "Las Bugas";
        String zipCode;
    }

}
