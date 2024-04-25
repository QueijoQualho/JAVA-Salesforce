package com.fiap.br.models.pessoa;

import com.fiap.br.models.Endereco;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Funcionario extends Usuario {
    private double salario;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Funcionario {\n");
        sb.append("  Id: ").append(getId()).append("\n");
        sb.append("  Nome: ").append(getNome()).append("\n");
        sb.append("  Telefone: ").append(getTelefone()).append("\n");
        sb.append("  Email: ").append(getEmail()).append("\n");
        sb.append("  CPF: ").append(getCpf()).append("\n");
        sb.append("  Cargo: ").append(getCargo()).append("\n");
        sb.append("  Senha: ").append(getSenha()).append("\n");
        sb.append("  Salario: ").append(salario).append("\n");
        sb.append("  Lista de Endereços:\n");
        for (Endereco endereco : getListaEnderecos()) {
            sb.append("    ").append(endereco).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
