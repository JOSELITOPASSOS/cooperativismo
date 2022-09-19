package com.act.cooperativism.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.domain.repository.PautaRepository;
import com.act.cooperativism.exception.NotFoundException;

@Service
public class PautaService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PautaService.class);
	
	@Autowired
	private PautaRepository repository;

	@Autowired
	private VotoService votoService;

	public List<Pauta> listar() {
		LOG.info("listando todas as pautas");
		return repository.findAll();
	}

	public Pauta cadastrar(Pauta entity) {
		LOG.info("Cadastrando uma nova pautas");
		return repository.save(entity);
	}

	public Pauta obter(Long id) {
		LOG.info("Obtendo uma pauta pelo id.");
		Optional<Pauta> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Pauta não encontrada."));
	}

	public Pauta resultadoVotacao(Pauta pauta) {
		LOG.info("Buscando Pauta");
		var entity = obter(pauta.getId());
		
		LOG.info("Registrando resultado da votação na pauta.");
		var totais = contarVotos(pauta);
		var sim = totais.get(1).longValue();
		var nao = totais.get(2).longValue();
		
		entity.setAprovada(sim > nao);
		var resultato = repository.saveAndFlush(entity);
		return resultato;
	}

	private List<Number> contarVotos(Pauta pauta) {
		LOG.info("Contabilizando votos.");
		var votos = votoService.listarVotosPorPauta(pauta.getId());
		long sim = votos.stream()
				.filter(v -> v.getOpcao().booleanValue())
				.count();
			
		long nao = votos.size() - sim;
		return  Arrays.asList(votos.size(), sim, nao);
	}
	
//	public void enviarResultadoVotacaoParaFila() {
//		Pauta pauta = repository.findById(Long.valueOf(1)).orElseThrow();
//		rabbitTemplate.convertAndSend(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingkey(), pauta);
//	}
	
}
