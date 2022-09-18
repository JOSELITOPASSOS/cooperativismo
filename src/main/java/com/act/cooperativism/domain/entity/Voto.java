package com.act.cooperativism.domain.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "associado_id")
	private Associado associado;
	
	@ManyToOne
	@JoinColumn(name = "sessao_id")
	private SessaoVotacao sessaoVotacao; 

}
