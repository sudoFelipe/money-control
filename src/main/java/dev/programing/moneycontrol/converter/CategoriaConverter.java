package dev.programing.moneycontrol.converter;

import dev.programing.moneycontrol.dto.requests.CategoriaRequestDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.model.Categoria;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class CategoriaConverter {


    public Categoria toPersistirCategoria(CategoriaRequestDTO requestDTO) {
        return Categoria.builder()
                .descricao(requestDTO.descricao())
                .sigla(requestDTO.sigla())
                .build();
    }

    public CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .descricao(categoria.getDescricao())
                .sigla(categoria.getSigla())
                .build();
    }
}
