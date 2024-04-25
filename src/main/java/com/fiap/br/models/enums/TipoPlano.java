package com.fiap.br.models.enums;

public enum TipoPlano {
    ANUAL ("Anual"), 
    SEMESTRAL ("Semestral"),
    TRIMESTRAL ("Trimestral"),
    MENSAL ("Mensal");
    
    private String descricao;

	private TipoPlano(String descricao) {
        this.descricao = descricao;
    }

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
}
