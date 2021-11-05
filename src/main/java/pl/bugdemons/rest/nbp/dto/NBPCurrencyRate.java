package pl.bugdemons.rest.nbp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NBPCurrencyRate {

    @JsonProperty("table")
    public String table;
    @JsonProperty("currency")
    public String currency;
    @JsonProperty("code")
    public String code;
    @JsonProperty("rates")
    public List<Rate> rates;
}
