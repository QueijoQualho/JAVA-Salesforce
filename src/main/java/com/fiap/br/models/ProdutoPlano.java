package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.Required;
import com.fiap.br.util.annotations.TableName;
import lombok.Data;

@Data
@TableName("ProdutoPlano")
public class ProdutoPlano {
    @JsonProperty("ID_PRODUTOPLANO")
    private Long id;

    @Required
    @JsonProperty("ID_PRODUTO")
    private Long idProduto;

    @Required
    @JsonProperty("ID_PLANO")
    private Long idPlano;

    @Required
    @JsonProperty("VALORTOTAL")
    private double valorTotal;
}
