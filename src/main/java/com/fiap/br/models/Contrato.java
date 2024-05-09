package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.models.enums.TipoPagamento;

import lombok.Data;

@Data
public class Contrato {
    private Long id;
    private Plano plano;
    private EmpresaCliente empresa;
    private TipoPagamento tipoPagamento;
}
