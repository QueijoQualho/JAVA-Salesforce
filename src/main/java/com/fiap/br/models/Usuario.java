package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.TableName;

import lombok.Data;

@Data
@TableName("Usuarios")
public class Usuario {
    @JsonProperty("id_usuario")
    private int id;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("email")
    private String email;
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("cargo")
    private String cargo;
    @JsonProperty("senha")
    private String senha;
    @JsonProperty("isAdmin")
    private Boolean isAdmin;
}