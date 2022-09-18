package com.act.cooperativism.domain.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Voto;
import com.act.cooperativism.domain.repository.VotoRepository;

@Service
public class VotoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(VotoService.class);
	
	@Autowired
	private VotoRepository repository;

	public List<Voto> listar() {
		LOG.info("Listantando todos os votos");
		return repository.findAll();
	}
	
	public Voto criar(Voto entity) {
		LOG.info("Cadastrando uma nova pautas");
		return repository.save(entity);
	}

	public Voto obter(Long id) {
		LOG.info("Obtendo uma Sessão de Vaotação pelo id.");
		Optional<Voto> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
