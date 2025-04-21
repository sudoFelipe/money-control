package dev.programing.moneycontrol.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoriaRequestDTO(

        @NotNull(message = "O campo descrição é obrigatório")
        @NotBlank(message = "O campo descrição não pode ser vazio")
        String descricao,

        @NotNull(message = "O campo descrição é obrigatório")
        @NotBlank(message = "O campo descrição não pode ser vazio")
        String sigla
) {}
