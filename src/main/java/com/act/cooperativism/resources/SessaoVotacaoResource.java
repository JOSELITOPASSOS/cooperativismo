package com.act.cooperativism.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.act.cooperativism.domain.entity.SessaoVotacao;
import com.act.cooperativism.services.SessaoVotacaoService;

@RestController
@RequestMapping("/sessoes")
public class SessaoVotacaoResource {

	@Autowired
	private SessaoVotacaoService service;
	
	@GetMapping
	public ResponseEntity<List<SessaoVotacao>> listar() {
		var lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<SessaoVotacao> obterSessao(@PathVariable Long id) {
		var obj = service.obter(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<SessaoVotacao> abrirSessao(@RequestBody SessaoVotacao entity) {
		var obj = this.service.abrir(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
	
	@PatchMapping
	public ResponseEntity<SessaoVotacao> encerrarSessao(@RequestBody SessaoVotacao entity) {
		entity.setFinalizada(true);
		var obj = this.service.encerra(entity);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).body(obj);
	}
}
