package com.act.cooperativism.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.SessaoVotacao;
import com.act.cooperativism.domain.repository.SessaoVotacaoRepository;
import com.act.cooperativism.exception.BadRequestException;

@Service
public class SessaoVotacaoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SessaoVotacaoService.class);
	
	@Autowired
	private SessaoVotacaoRepository repository;

	public List<SessaoVotacao> listar() {
		LOG.info("Listando todas as sessões de votação");
		return repository.findAll();
	}
	
	public SessaoVotacao obter(Long id) {
		LOG.info("Obtendo uma sessão de votação pelo id.");
		Optional<SessaoVotacao> obj = repository.findById(id);
		return obj.orElseThrow(() -> new BadRequestException("Sessão de votação não encontrada."));
	}

	public SessaoVotacao abrir(SessaoVotacao entity) {
		LOG.info("Abrindo uma nova sessão de votação");
		return repository.save(entity);
	}

	public SessaoVotacao encerra(SessaoVotacao entity) {
		LOG.info("Encerrando sessão de votação.");
		return repository.saveAndFlush(entity);
	}

}
