package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Produto {
    @JsonProperty("id_produto")
    private Long id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("preco")
    private double preco;

}
