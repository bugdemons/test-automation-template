package pl.bugdemons.rest.nbp.dto.params;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class QueryParamsDto {

    FormatEnum format;
}
