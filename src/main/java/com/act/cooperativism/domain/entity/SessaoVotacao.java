package com.act.cooperativism.domain.entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class SessaoVotacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private LocalDateTime dataInicio;
	
	@Column(nullable = false)
	private LocalDateTime dataFim;
	
	@Builder.Default
	@Column(nullable = false)
	private Duration duracao = Duration.parse("PT1M");
	
	@Builder.Default
	@Column(nullable = false)
	private Boolean finalizada = false;
	
	@ManyToOne
	@JoinColumn(name = "pauta_id")
	private Pauta pauta;
	
	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = this.gerarDataFim(this.dataInicio, this.duracao);
	}
	
	private LocalDateTime gerarDataFim(LocalDateTime dataInicio, Duration duracao) {
		var temp = getDataInicio();
		var minutes = duracao.toMinutes(); 
		return temp.plusMinutes(minutes);
	}
}
