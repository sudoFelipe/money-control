package dev.programing.moneycontrol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("categoria")
public class Categoria {

    private String id;

    @Field("descricao")
    private String descricao;

    @Field("sigla")
    private String sigla;
}
