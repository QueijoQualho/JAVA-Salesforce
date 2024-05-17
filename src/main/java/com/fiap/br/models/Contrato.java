package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.models.enums.TipoPagamento;

import com.fiap.br.util.annotations.TableName;
import lombok.Data;

@Data
@TableName("Contratos")
public class Contrato {
    @JsonProperty("id_contrato")
    private int id;
    @JsonProperty("id_cliente")
    private int idPlano;
    @JsonProperty("id_produto_plano")
    private int idUsuario;
    @JsonProperty("tipo_Plano")
    private TipoPagamento tipoPagamento;
}
