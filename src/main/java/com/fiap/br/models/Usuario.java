package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.Required;
import com.fiap.br.util.annotations.TableName;

import lombok.Data;

@Data
@TableName("Usuarios")
public class Usuario {
    @JsonProperty("id_usuario")
    private int id;

    @Required
    @JsonProperty("nome")
    private String nome;

    @Required
    @JsonProperty("telefone")
    private String telefone;

    @Required
    @JsonProperty("email")
    private String email;

    @Required
    @JsonProperty("cpf")
    private String cpf;

    @Required
    @JsonProperty("cargo")
    private String cargo;

    @Required
    @JsonProperty("senha")
    private String senha;

    @Required
    @JsonProperty("nome_Empresa")
    private String nomeEmpresa;

    @Required
    @JsonProperty("cnpj")
    private String cnpj;

    @JsonProperty("isAdmin")
    private Boolean isAdmin;
}