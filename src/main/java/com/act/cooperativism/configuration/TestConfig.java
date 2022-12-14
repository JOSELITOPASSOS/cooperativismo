package com.act.cooperativism.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.act.cooperativism.services.DBService;

@Configuration
//@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instancia() throws Exception {
		this.dbService.instanciaBaseDeDados();
		return true;
	}
}
