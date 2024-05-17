package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.util.annotations.TableName;
import lombok.Data;

@Data
@TableName("EmpresaCliente")
public class EmpresaCliente {
    @JsonProperty("ID_EMPRESACLIENTE")
    private Long id;
    @JsonProperty("CNPJ")
    private String cnpj;
    @JsonProperty("NOMEFANTASIA")
    private String nomeFantasia;
    @JsonProperty("ID_EMPRESACLIENTE")
    private int idCliente;
}
