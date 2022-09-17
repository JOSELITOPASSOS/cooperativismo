package com.act.cooperativism.domain.entity;

public enum VotoEnum {
	
	SIM(1, "Sim"),
	NAO(0, "Não");

	private final int id;
	private final String descricao;
	
	VotoEnum(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
}
