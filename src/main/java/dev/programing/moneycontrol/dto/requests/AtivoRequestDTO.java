package dev.programing.moneycontrol.dto.requests;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record AtivoRequestDTO(

        @NotBlank(message = "O campo ticker é obrigatório")
        String ticker,

        @NotBlank(message = "O campo CNPJ é obrigatório")
        @CNPJ(message = "O campo CNPJ é inválido")
        String cnpj,

        @NotBlank(message = "O campo idCategoria é obrigatório")
        String idCategoria
) {}
