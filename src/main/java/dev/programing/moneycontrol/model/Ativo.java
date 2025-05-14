package dev.programing.moneycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("ativo")
public class Ativo {

    private String id;
    private String ticker;
    private String descricao;
    private String cnpj;
    private Categoria categoria;
}
