package com.act.cooperativism.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.SessaoVotacao;
import com.act.cooperativism.domain.repository.SessaoVotacaoRepository;

@Service
public class SessaoVotacaoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SessaoVotacaoService.class);
	
	@Autowired
	private SessaoVotacaoRepository repository;

	public List<SessaoVotacao> listar() {
		LOG.info("Listantando todas as pautas");
		return repository.findAll();
	}
	
	public SessaoVotacao obterSessao(Long id) {
		LOG.info("Obtendo uma Sessão de Vaotação pelo id.");
		Optional<SessaoVotacao> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public SessaoVotacao abrir(SessaoVotacao entity) {
		LOG.info("Abrindo uma nova sessão");
		return repository.save(entity);
	}

	public SessaoVotacao abrirSessao(SessaoVotacao entity) {
		LOG.info("Abindo nova Sessão.");
		return repository.save(entity);
	}

	public SessaoVotacao encerra(SessaoVotacao entity) {
		LOG.info("Encerrando sessão");
		return repository.saveAndFlush(entity);
	}

}
