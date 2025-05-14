package dev.programing.moneycontrol.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;

public record AtivoRequestDTO(

        @NotBlank
        @NotNull
        String ticker,

        @NotBlank
        @NotNull
        String descricao,

        @NotBlank
        @NotNull
        @CNPJ
        String cnpj,

        @NotBlank
        @NotNull
        String idCategoria
) {}
