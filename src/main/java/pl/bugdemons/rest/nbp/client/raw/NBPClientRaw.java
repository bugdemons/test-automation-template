package pl.bugdemons.rest.nbp.client.raw;

import java.util.Map;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import pl.bugdemons.rest.nbp.dto.params.TableEnum;

import static io.restassured.RestAssured.given;

public class NBPClientRaw {

    private final static String RATE_URI = "/exchangerates/rates/{table}/{currency}";
    private final static String RATE_DATE_URI = RATE_URI + "/{date}";

    public ValidatableResponse getCurrencyRate(String currency, TableEnum table, String date, Map<String, Object> queryParams) {
        return getRequestSpecification()
                .pathParam("currency", currency)
                .pathParam("table", table)
                .pathParam("date", date)
                .queryParams(queryParams)
                .get(RATE_DATE_URI)
                .then();
    }

    private RequestSpecification getRequestSpecification() {
        return given()
                .baseUri("http://api.nbp.pl/api/");
    }
}
