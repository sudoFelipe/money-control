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
public class AtivoResponseDTO {

    @Schema(description = "Identificador do ativo", defaultValue = "68069374e5c572004254668d")
    private String id;

    @Schema(description = "Descrição do ativo", defaultValue = "Klabin S.A")
    private String descricao;

    @Schema(description = "Ticker do ativo", defaultValue = "KLBN4")
    private String ticker;

    @Schema(description = "CNPJ do ativo", defaultValue = "99709151000180")
    private String cnpj;

    private CategoriaResponseDTO categoria;

    @Schema(description = "Link de visualização da imagem", defaultValue = "https://icons.brapi.dev/icons/KLBN4.svg")
    private String imagem;

    @Schema(description = "Tipo de moeda relacionada ao ativo", defaultValue = "BRL")
    private String moeda;
}
