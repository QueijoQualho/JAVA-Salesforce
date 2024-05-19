package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.Required;
import com.fiap.br.util.annotations.TableName;

import lombok.Data;

@Data
@TableName("produtos")
public class Produto {
    @JsonProperty("id_produto")
    private int id;

    @Required
    @JsonProperty("nome")
    private String nome;

    @Required
    @JsonProperty("preco")
    private double preco;

}
