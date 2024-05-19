package com.fiap.br.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.models.enums.TipoPlano;
import com.fiap.br.util.annotations.Required;
import com.fiap.br.util.annotations.TableName;

import lombok.Data;

@Data
@TableName("Planos")
public class Plano {
    @JsonProperty("ID_PLANO")
    private int id;

    @Required
    @JsonProperty("NOMEFANTASIA")
    private String nomeFantasia;

    @Required
    @JsonProperty("TIPOPLANO")
    private TipoPlano tipoPlano;

    @Required
    @JsonProperty("DATAINICIO")
    private LocalDate dataInicio;

    @Required
    @JsonProperty("DATAFINAL")
    private LocalDate dataFinal;

}
