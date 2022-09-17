package com.act.cooperativism.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.cooperativism.domain.entity.Pauta;

@Service
public class DBService {

	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);
	
	@Autowired
	private PautaService pautaService; 
	
	public void instanciaBaseDeDados() {
		LOG.info("Modeo de teste");
		var pauta = new Pauta();
		pauta.setNome("Pauta Teste");
		
		pautaService.cadastra(pauta);
	}

}
