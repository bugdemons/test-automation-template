package pl.bugdemons.rest.nbp.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rate {

    @JsonProperty("no")
    public String no;
    @JsonProperty("effectiveDate")
    public String effectiveDate;
    @JsonProperty("bid")
    public BigDecimal bid;
    @JsonProperty("ask")
    public BigDecimal ask;

}
