package com.fiap.br.models.enums;

public enum TipoPagamento {
    PIX ("PIX"), 
    CREDITO("Crédito"),  
    DEBITO("Débito"),
    BOLETO("Boleto");
    
    private String descricao;

	private TipoPagamento(String descricao) {
        this.descricao = descricao;
    }

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "TipoDePagamento [descricao=" + descricao + "]"; 
	}
}
