package com.fiap.br.models;

import com.fiap.br.models.enums.TipoPagamento;
import com.fiap.br.models.pessoa.Funcionario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contrato {
    private Long id;
    private Plano plano;
    private EmpresaCliente empresa;
    private TipoPagamento tipoPagamento;

    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", plano=" + plano +
                ", funcionario=" + funcionario +
                ", empresa=" + empresa +
                ", tipoPagamento=" + tipoPagamento +
                '}';
    }

}
