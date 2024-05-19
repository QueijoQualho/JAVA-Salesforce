package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.Required;
import com.fiap.br.util.annotations.TableName;
import lombok.Data;

@Data
@TableName("ProdutosPlano")
public class ProdutoPlano {
    @JsonProperty("ID_PRODUTOPLANO")
    private int id;

    @Required
    @JsonProperty("ID_PRODUTO")
    private int idProduto;

    @Required
    @JsonProperty("ID_PLANO")
    private int idPlano;

    @JsonProperty("VALORTOTAL")
    private double valorTotal;
}
