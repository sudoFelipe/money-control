package dev.programing.moneycontrol.docs;

import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.model.Categoria;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static dev.programing.moneycontrol.utils.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Categoria de ativos", description = "Gerenciamento da Categoria de ativos")
public interface CategoriaDocumentation {

    @Operation(summary = "Criar Categoria", description = "Cria nova categoria de ativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED, description = CREATED_CODE, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = CategoriaResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<CategoriaResponseDTO> criarCategoria(CategoriaRequestDTO requestDTO);

    @Operation(summary = "Consultar Categorias", description = "Consulta as categorias dos ativos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS, description = SUCCESS_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = NOT_FOUND, description = NOT_FOUND_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST, description = BAD_REQUEST_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR, description = INTERNAL_SERVER_ERROR_CODE, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Flux<CategoriaResponseDTO> consultarCategorias();
}
