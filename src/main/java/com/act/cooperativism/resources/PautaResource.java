package com.act.cooperativism.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.services.PautaService;

@RestController
@RequestMapping("/pautas")
public class PautaResource {
	
	@Autowired
	private PautaService service;
	
	@GetMapping
	public ResponseEntity<List<Pauta>> listar() {
		return ResponseEntity.ok().body( this.service.listar() );
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pauta> obterPauta(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.obter(id) );
	}
	
	@PostMapping
	public ResponseEntity<Pauta> cadastrarPauta(@RequestBody Pauta entity) {
		var obj = this.service.cadastrar(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}

}
