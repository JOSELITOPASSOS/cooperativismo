package com.act.cooperativism.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.act.cooperativism.domain.converter.BooleanSimNaoConverter;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Voto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "associado")
	private Associado associado;
	
	@ManyToOne
	@JoinColumn(name = "sessao")
	private SessaoVotacao sessaoVotacao; 

	@Column(nullable = false)
	@Convert(converter = BooleanSimNaoConverter.class)
	private Boolean opcao;
}
