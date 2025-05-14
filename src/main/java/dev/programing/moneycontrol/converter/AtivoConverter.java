package dev.programing.moneycontrol.converter;

import dev.programing.moneycontrol.dto.requests.AtivoRequestDTO;
import dev.programing.moneycontrol.dto.responses.AtivoResponseDTO;
import dev.programing.moneycontrol.dto.responses.CategoriaResponseDTO;
import dev.programing.moneycontrol.model.Ativo;
import dev.programing.moneycontrol.model.Categoria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AtivoConverter {


    public Ativo toAtivoDTO(AtivoRequestDTO requestDTO, Categoria categoria) {
        return Ativo.builder()
                .cnpj(requestDTO.cnpj())
                .ticker(requestDTO.ticker())
                .descricao(requestDTO.descricao())
                .categoria(categoria)
                .build();
    }

    public AtivoResponseDTO toAtivoResponseDTO(Ativo ativo) {
        return AtivoResponseDTO.builder()
                .id(ativo.getId())
                .ticker(ativo.getTicker())
                .cnpj(ativo.getCnpj())
                .categoria(toCategoriaResponseDTO(ativo.getCategoria()))
                .build();
    }

    private CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria) {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .descricao(categoria.getDescricao())
                .sigla(categoria.getSigla())
                .build();
    }
}
