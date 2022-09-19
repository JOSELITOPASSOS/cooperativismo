package com.act.cooperativism.services;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Associado;
import com.act.cooperativism.domain.repository.AssociadoRepository;
import com.act.cooperativism.exception.BadRequestException;
import com.act.cooperativism.exception.NotFoundException;
import com.act.cooperativism.exception.UnprocessableEntityException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AssociadoService {

	private static final Logger LOG = LoggerFactory.getLogger(AssociadoService.class);

	@Autowired
	private AssociadoRepository repository;

	public List<Associado> listar() {
		LOG.info("listantando todos associados");
		return repository.findAll();
	}

	public Associado obter(Long id) {
		LOG.info("Obtendo Associado pelo id.");
		Optional<Associado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new NotFoundException("Associado não encontrado."));
	}

	public Associado cadastrar(Associado entity) {
		LOG.info("Cadastrando novo associado.");
		return repository.save(entity);
	}

	public Object associadoHabilitado(String cpf) {
		LOG.info("Validar se Associado pode votar.");
		ObjectMapper mapper = new ObjectMapper();
		try (CloseableHttpClient client = HttpClients.createDefault()) {
			 HttpGet request = new HttpGet("https://user-info.herokuapp.com/users/" + cpf);
			 Object response = client.execute(
					request, 
					httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Object.class)
 	        );

			return response;
		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage());
			e.printStackTrace();
			throw null;
		}		

	}

	@SuppressWarnings("unchecked")
	public Associado podeVotar(Associado associado) throws Exception {
		LOG.info("Cadastrando novo associado.");
		
		if(Objects.isNull(associado.getId())) {
		   throw new BadRequestException("Associado não informado.");
		}
		var habilitado = obter(associado.getId());
		
		var status = (HashMap<String, String>) associadoHabilitado(habilitado.getCpf());
		if(status.containsValue("UNABLE_TO_VOTE")) {
			throw new UnprocessableEntityException("Associado não habilitado para votar.");
		}
		
		return habilitado;
			
	}
	
}
