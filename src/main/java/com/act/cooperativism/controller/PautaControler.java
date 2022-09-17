package com.act.cooperativism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.act.cooperativism.model.Pauta;
import com.act.cooperativism.repository.PautaRepository;

/**
 * @author Joselito
 */
@RestController
@RequestMapping("/pautas")
public class PautaControler {
	
	@Autowired
	private PautaRepository repository;
	
	@GetMapping
	public List<Pauta> listar() {
		return repository.findAll();
		
	}

}
