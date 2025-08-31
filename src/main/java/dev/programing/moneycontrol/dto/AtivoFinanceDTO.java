package dev.programing.moneycontrol.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import dev.programing.moneycontrol.enums.TipoMoedaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtivoFinanceDTO {

    @JsonAlias(value = "symbol")
    private String ticker;

    @JsonAlias(value = "currency")
    private TipoMoedaEnum moeda;

    @JsonAlias(value = "longName")
    private String nome;

    @JsonAlias(value = "regularMarketPrice")
    private Double valorMercado;

    @JsonAlias(value = "logourl")
    private String imagemAtivo;
}
