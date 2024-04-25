package com.fiap.br.models.pessoa;

import java.util.ArrayList;
import java.util.List;

import com.fiap.br.models.Endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Usuario {
    private Long id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private String cargo;
    private String senha;
    private List<Endereco> listaEnderecos = new ArrayList<>();

    public void addEndereco(Endereco endereco) {
        this.listaEnderecos.add(endereco);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario {\n");
        sb.append("  Id: ").append(id).append("\n");
        sb.append("  Nome: ").append(nome).append("\n");
        sb.append("  Telefone: ").append(telefone).append("\n");
        sb.append("  Email: ").append(email).append("\n");
        sb.append("  CPF: ").append(cpf).append("\n");
        sb.append("  Cargo: ").append(cargo).append("\n");
        sb.append("  Senha: ").append(senha).append("\n");
        sb.append("  Lista de Endereços:\n");
        for (Endereco endereco : listaEnderecos) {
            sb.append("    ").append(endereco).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

}
