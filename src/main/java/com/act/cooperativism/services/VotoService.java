package com.act.cooperativism.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.SessaoVotacao;
import com.act.cooperativism.domain.entity.Voto;
import com.act.cooperativism.domain.repository.VotoRepository;

@Service
public class VotoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(VotoService.class);
	
	@Autowired
	private VotoRepository repository;

	@Autowired
	private SessaoVotacaoService sessaoService;
	
	@Autowired
	private AssociadoService associadoService;
	
	public List<Voto> listar() {
		LOG.info("Listantando todos os votos");
		return repository.findAll();
	}

	public Voto obter(Long id) {
		LOG.info("Obtendo uma Sessão de Vaotação pelo id.");
		Optional<Voto> obj = repository.findById(id);
		return obj.orElse(null);
	}
	
	public List<Voto> registrarEmlote(List<Voto> lote) {
		return this.repository.saveAll(lote);
	}

	public Voto registrar(Voto entity) throws Exception {
		LOG.info("Verificadno validade do voto.");
		votoValido(entity);
		
		LOG.info("Registrando voto.");
		return repository.save(entity);
	}
	
	public List<Voto> listarVotosPorPauta(Long pautaId) {
		var votos = repository.findBySessaoVotacaoPautaId(pautaId);
		return votos;
	}

	private Voto votoValido(Voto voto) throws Exception {
		var sessao = validarSessao(voto);
		voto.setSessaoVotacao(sessao);
		
		if(Objects.isNull(voto.getAssociado())) {
		   throw new Exception("Associado não informado.");
		}
		
		var associado = associadoService.podeVotar(voto.getAssociado());
		voto.setAssociado(associado);

		var qtd = repository.verificarSeVotou(associado.getId(), sessao.getPauta().getId());
		if(qtd > 0) {
		   throw new Exception("Associado já registrou voto para essa pauta.");
		}
		
		return voto;
	}

	private SessaoVotacao validarSessao(Voto entity) throws Exception {
		if(Objects.isNull(entity.getSessaoVotacao())) {
			throw new Exception("Sessão não informada.");
		}
		var sessao = sessaoService.obterSessao(entity.getSessaoVotacao().getId());
		
		if(sessao.getFinalizada()) {
			throw new Exception("Sessão encerrada.");
		}
		return sessao;
	}

}
