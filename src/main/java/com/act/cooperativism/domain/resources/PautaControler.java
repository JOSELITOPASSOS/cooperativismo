package com.act.cooperativism.domain.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.act.cooperativism.domain.model.Pauta;
import com.act.cooperativism.services.PautaService;

/**
 * @author Joselito
 */
@RestController
@RequestMapping("/pautas")
public class PautaControler {
	
	@Autowired
	private PautaService service;
	
	@GetMapping
	public List<Pauta> listar() {
		return service.listar();						
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pauta adicionarPauta(@RequestBody Pauta pauta) {
		return service.cadastra(pauta);
	}

}
