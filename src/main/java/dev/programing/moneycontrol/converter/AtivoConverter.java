package dev.programing.moneycontrol.converter;

import dev.programing.moneycontrol.dto.AtivoFinanceDTO;
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


    public Ativo toAtivo(AtivoRequestDTO requestDTO, AtivoFinanceDTO ativo, Categoria categoria) {
        return Ativo.builder()
                .ticker(ativo.getTicker())
                .descricao(ativo.getNome())
                .cnpj(requestDTO.cnpj())
                .categoria(categoria)
                .linkImagem(ativo.getImagemAtivo())
                .moeda(ativo.getMoeda().toString())
                .build();
    }

    public AtivoResponseDTO toAtivoResponseDTO(Ativo ativo) {
        return AtivoResponseDTO.builder()
                .id(ativo.getId())
                .ticker(ativo.getTicker())
                .cnpj(ativo.getCnpj())
                .categoria(toCategoriaResponseDTO(ativo.getCategoria()))
                .imagem(ativo.getLinkImagem())
                .moeda(ativo.getMoeda())
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
