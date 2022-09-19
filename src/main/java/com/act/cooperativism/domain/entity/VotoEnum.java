package com.act.cooperativism.domain.entity;

public enum VotoEnum {
	
	SIM(true, "Sim"),
	NAO(false, "Não");

	private final Boolean opcao;
	private final String descricao;
	
	VotoEnum(Boolean opcao, String descricao) {
		this.opcao = opcao;
		this.descricao = descricao;
	}
	
	public Boolean getOpcao() {
		return opcao;
	}

	public String getDescricao() {
		return descricao;
	}
}
