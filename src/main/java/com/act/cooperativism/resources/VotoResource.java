package com.act.cooperativism.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.act.cooperativism.domain.entity.Voto;
import com.act.cooperativism.services.VotoService;

@RestController
@RequestMapping("/votos")
public class VotoResource {

	@Autowired
	private VotoService service;
	
	@GetMapping
	public ResponseEntity<List<Voto>> listar() {
		return ResponseEntity.ok().body( this.service.listar() );
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Voto> obterVoto(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.obter(id) );
	}
}
