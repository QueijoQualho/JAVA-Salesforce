package com.fiap.br.models;

import lombok.Data;

@Data
public class Endereco {
    private Long id;
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
}
