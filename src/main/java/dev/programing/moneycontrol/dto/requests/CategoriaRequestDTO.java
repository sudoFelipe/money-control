package dev.programing.moneycontrol.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoriaRequestDTO(

        @NotNull(message = "{required.descricao}")
        @NotBlank(message = "{blank.descricao}")
        String descricao,

        @NotNull(message = "{required.sigla}")
        @NotBlank(message = "{blank.sigla}")
        String sigla
) {}
