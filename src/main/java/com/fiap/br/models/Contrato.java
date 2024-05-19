package com.fiap.br.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.br.models.enums.TipoPagamento;
import com.fiap.br.util.annotations.Required;
import com.fiap.br.util.annotations.TableName;
import lombok.Data;

@Data
@TableName("Contratos")
public class Contrato {
    @JsonProperty("id_contrato")
    private int id;
    
    @Required
    @JsonProperty("id_cliente")
    private int idPlano;

    @Required
    @JsonProperty("id_produto_plano")
    private int idUsuario;

    @Required
    @JsonProperty("tipo_Pagamento")
    private TipoPagamento tipoPagamento;
}
