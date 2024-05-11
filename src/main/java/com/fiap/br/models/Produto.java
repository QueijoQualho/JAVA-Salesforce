package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.TableName;

import lombok.Data;

@Data
@TableName("produtos")
public class Produto {
    @JsonProperty("id_produto")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("preco")
    private Double preco;

}
