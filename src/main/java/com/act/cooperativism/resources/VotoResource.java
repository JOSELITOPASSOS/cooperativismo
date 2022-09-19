package com.act.cooperativism.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<Voto> registrarVoto(@RequestBody Voto entity) throws Exception {
		var obj = this.service.registrar(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}
	
	@PostMapping("/lote")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Voto>> registrarVotoEmLote(@RequestBody List<Voto> lote) {
		var obj = this.service.registrarEmlote(lote);
		return ResponseEntity.ok().body(obj);
	}
}
