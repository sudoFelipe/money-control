package dev.programing.moneycontrol.docs;

import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static dev.programing.moneycontrol.utils.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Categorias", description = "Gerenciamento da Categoria de ativos")
@Validated
public interface CategoriaDocumentation {

    @Operation(summary = "Criar Categoria", description = "Cria nova categoria de ativo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = CREATED_CODE, description = CREATED, content =
                    { @Content(mediaType = APPLICATION_JSON_VALUE, schema =
                    @Schema(implementation = CategoriaResponseDTO.class)) }),
            @ApiResponse(responseCode = NOT_FOUND_CODE, description = NOT_FOUND, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<CategoriaResponseDTO> criarCategoria(@Valid CategoriaRequestDTO requestDTO);

    @Operation(summary = "Consultar Categorias", description = "Consulta as categorias dos ativos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = SUCCESS,
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = CategoriaResponseDTO.class)))),
            @ApiResponse(responseCode = NOT_FOUND_CODE, description = NOT_FOUND, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Flux<CategoriaResponseDTO> consultarCategorias();

    @Operation(summary = "Remover Categoria", description = "Remove a categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS_NO_CONTENT_CODE, description = SUCCESS),
            @ApiResponse(responseCode = NOT_FOUND_CODE, description = NOT_FOUND, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = BAD_REQUEST_CODE, description = BAD_REQUEST, content = @Content(mediaType = APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = INTERNAL_SERVER_ERROR_CODE, description = INTERNAL_SERVER_ERROR, content = @Content(mediaType = APPLICATION_JSON_VALUE))})
    public Mono<Void> removerCategoria(String idCategoria);
}
