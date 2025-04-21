package dev.programing.moneycontrol.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(NON_NULL)
public class CategoriaResponseDTO {

    @Schema(description = "Identificador da categoria", defaultValue = "381hj3h41")
    private String id;

    @Schema(description = "Descrição da categoria", defaultValue = "Fundo Imobiliário")
    private String descricao;

    @Schema(description = "Sigla da categoria", defaultValue = "FII")
    private String sigla;

}
