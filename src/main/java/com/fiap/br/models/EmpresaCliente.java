package com.fiap.br.models;

import lombok.Data;

@Data
public class EmpresaCliente {
    private Long id;
    private Usuario cliente;
    private String cnpj;
    private String telefone;
    private String razaoSocial;
    private String nomeFantasia;
    private int tamanho;

}
