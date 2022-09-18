package com.act.cooperativism.services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Associado;
import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.domain.entity.SessaoVotacao;

@Service
public class DBService {

	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);

	@Autowired
	private PautaService pautaService; 

	@Autowired
	private SessaoVotacaoService sessaoService;

	@Autowired
	private AssociadoService associadoService;

	public void instanciaBaseDeDados() {
		LOG.info("Criando modeo de teste");

		var pauta = novaPauta();
		pautaService.cadastrar(pauta);

		var sessao = novaSessao(pauta);
		sessaoService.abrir(sessao);

		var associado = novoAssociado();
		associadoService.cadastrar(associado);
	}

	private Associado novoAssociado() {
		var associado = Associado.builder()
				.nome("Associado Teste")
				.cpf("55674527059")
				.build();
		return associado;
	}

	private SessaoVotacao novaSessao(Pauta pauta) {
		var sessao = SessaoVotacao.builder()				
				.dataInicio(LocalDateTime.now())				
				.dataFim(LocalDateTime.now())
				.pauta(pauta)
				.build();

		return sessao;
	}

	private Pauta novaPauta() {
		var pauta = Pauta.builder()
				.descricao("Teste de votação")
				.data(LocalDateTime.now())
				.aprovada(true)
				.build();

		return pauta;
	}

}
