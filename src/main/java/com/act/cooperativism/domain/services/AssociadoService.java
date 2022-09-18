package com.act.cooperativism.domain.services;

import java.util.List;
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

	public Associado obterAssociado(Long id) {
		LOG.info("Obtendo Associado pelo id.");
		Optional<Associado> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public Associado cadastrar(Associado entity) {
		LOG.info("Cadastrando novo associado.");
		return repository.save(entity);
	}

	public Object verificar(String cpf) {

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
			return null;
		}		

	}

}
