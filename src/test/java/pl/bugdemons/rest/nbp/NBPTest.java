package pl.bugdemons.rest.nbp;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import pl.bugdemons.rest.nbp.client.NBPClient;
import pl.bugdemons.rest.nbp.dto.params.FormatEnum;
import pl.bugdemons.rest.nbp.dto.params.QueryParamsDto;
import pl.bugdemons.rest.nbp.dto.params.TableEnum;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SoftAssertionsExtension.class)
public class NBPTest {

    private final static NBPClient NBP = new NBPClient();

    @Test
    void dataAsJson(SoftAssertions softly) {
        var response = NBP.getCurrencyRate(
                "USD",
                TableEnum.C,
                LocalDate.of(2016, 4, 20),
                QueryParamsDto.builder().format(FormatEnum.JSON).build()
        );

        softly.assertThat(response).contains("3.7421");
        softly.assertThat(response).contains("{", "}");
        softly.assertThat(response).doesNotContain("<", ">");
    }

    @Test
    void dataAsXml(SoftAssertions softly) {
        var response = NBP.getCurrencyRate(
                "USD",
                TableEnum.C,
                LocalDate.of(2016, 4, 20),
                QueryParamsDto.builder().format(FormatEnum.XML).build()
        );

        softly.assertThat(response).contains("3.7421");
        softly.assertThat(response).contains("<", ">");
        softly.assertThat(response).doesNotContain("{", "}");
    }

    @Test
    void dataAsDto() {
        var nbpResponse = NBP.getCurrencyRate(
                "USD",
                TableEnum.C,
                LocalDate.of(2016, 4, 20)
        );
        assertThat(nbpResponse.getRates().get(0).getBid()).isEqualTo(new BigDecimal("3.7421"));
    }

}
