package dev.programing.moneycontrol.docs;

import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import static dev.programing.moneycontrol.utils.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Ativos", description = "Gerenciamento dos Ativos")
public interface AtivoControllerDocumentation {

    @Operation(summary = "Adicionar novo ativo", description = "Adiciona novo ativo na carteira de investimentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED_CODE, description = CREATED, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = AtivoResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND_CODE, description = NOT_FOUND, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<AtivoResponseDTO> AdicionarAtivo(AtivoRequestDTO requestDTO);
}
