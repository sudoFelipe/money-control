package dev.programing.moneycontrol.client.finance.dto;

import dev.programing.moneycontrol.dto.AtivoFinanceDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtivoFinanceResponseDTO {

    @Builder.Default
    private List<AtivoFinanceDTO> results = new ArrayList<>();
}
