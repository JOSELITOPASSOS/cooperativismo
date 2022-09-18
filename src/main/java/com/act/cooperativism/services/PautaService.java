package com.act.cooperativism.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.domain.repository.PautaRepository;

@Service
public class PautaService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository repository;

	public List<Pauta> listar() {
		LOG.info("listantando todas as pautas");
		return repository.findAll();
	}

	public Pauta cadastrar(Pauta entity) {
		LOG.info("Cadastrando uma nova pautas");
		return repository.save(entity);
	}

	public Pauta obter(Long id) {
		LOG.info("Obtendo uma Sessão de Vaotação pelo id.");
		Optional<Pauta> obj = repository.findById(id);
		return obj.orElse(null);
	}
	

}
