package com.fiap.br.models;

import com.fiap.br.models.pessoa.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaCliente {
    private Long id;
    private Cliente cliente;
    private String cnpj;
    private String telefone;
    private String razaoSocial;
    private String nomeFantasia;
    private int tamanho;

    @Override
    public String toString() {
        return "EmpresaCliente{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", cnpj='" + cnpj + '\'' +
                ", telefone='" + telefone + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", tamanho=" + tamanho +
                '}';
    }

}
