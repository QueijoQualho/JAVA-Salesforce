package com.fiap.br.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String cargo;
    private String senha;
    private Boolean isAdmin;
    private List<Endereco> listaEnderecos = new ArrayList<>();

    public void addEndereco(Endereco endereco) {
        this.listaEnderecos.add(endereco);
    }
}