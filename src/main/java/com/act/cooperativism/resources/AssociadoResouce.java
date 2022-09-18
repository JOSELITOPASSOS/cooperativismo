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

import com.act.cooperativism.domain.entity.Associado;
import com.act.cooperativism.services.AssociadoService;

@RestController
@RequestMapping("/associados")
public class AssociadoResouce {

	@Autowired
	private AssociadoService service;
	
	@GetMapping
	public ResponseEntity<List<Associado>> listar() {
		return ResponseEntity.ok().body(this.service.listar() );
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Associado> obterPauta(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.obterAssociado(id) );
	}
	
	@PostMapping
	public ResponseEntity<Associado> cadastrarAssociado(@RequestBody Associado entity) {
		var obj = this.service.cadastrar(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@GetMapping(value = "/{id}/validar/{cpf}")
	public ResponseEntity<Object> verificarAssociado(@PathVariable String cpf) {
		return ResponseEntity.ok().body(this.service.verificar(cpf) );
	}
}
