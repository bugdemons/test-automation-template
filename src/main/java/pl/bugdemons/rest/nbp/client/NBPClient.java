package pl.bugdemons.rest.nbp.client;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

import pl.bugdemons.rest.nbp.client.raw.NBPClientRaw;
import pl.bugdemons.rest.nbp.dto.NBPCurrencyRate;
import pl.bugdemons.rest.nbp.dto.params.FormatEnum;
import pl.bugdemons.rest.nbp.dto.params.QueryParamsDto;
import pl.bugdemons.rest.nbp.dto.params.TableEnum;
import pl.bugdemons.utils.ObjectMapperUtils;

@Slf4j
public class NBPClient {

    NBPClientRaw rawClient = new NBPClientRaw();

    public String getCurrencyRate(String currency, TableEnum table, LocalDate date, QueryParamsDto queryParamsDto) {
        var queryParams = ObjectMapperUtils.convertToNonNullValueMap(queryParamsDto);

        return rawClient.getCurrencyRate(currency, table, date.toString(), queryParams)
                .statusCode(200)
                .extract()
                .asPrettyString();
    }

    public NBPCurrencyRate getCurrencyRate(String currency, TableEnum table, LocalDate date) {
        var queryParamsDto = QueryParamsDto.builder().format(FormatEnum.JSON).build();
        var queryParams = ObjectMapperUtils.convertToNonNullValueMap(queryParamsDto);

        return rawClient.getCurrencyRate(currency, table, date.toString(), queryParams)
                .statusCode(200)
                .extract()
                .as(NBPCurrencyRate.class);
    }

}
