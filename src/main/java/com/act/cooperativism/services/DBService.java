package com.act.cooperativism.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.domain.entity.SessaoVotacao;

@Service
public class DBService {

	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);
	
	@Autowired
	private PautaService pautaService; 
	
	@Autowired
	private SessaoVotacaoService sessaoService;
	
	public void instanciaBaseDeDados() {
		LOG.info("Criando modeo de teste");
		var pauta = novaPauta();		
		
		pautaService.cadastrar(pauta);
		
		var sessao = new SessaoVotacao();
		sessao.setDescricao("Teste de votação");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		sessao.setDataFinalizar(LocalDateTime.parse("17/09/2022 00:00", formatter));
		sessao.setPauta(pauta);
		
		sessaoService.abrir(sessao);
	}

	private Pauta novaPauta() {
		var pauta = new Pauta();
		pauta.setNome("Pauta Teste");
		return pauta;
	}

}
