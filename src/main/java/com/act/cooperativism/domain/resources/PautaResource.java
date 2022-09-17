package com.act.cooperativism.domain.resources;

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

import com.act.cooperativism.domain.entity.Pauta;
import com.act.cooperativism.services.PautaService;

@RestController
@RequestMapping("/pautas")
public class PautaResource {
	
	@Autowired
	private PautaService service;
	
	@GetMapping
	public ResponseEntity<List<Pauta>> listar() {
		var lista = service.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Pauta> obterPauta(@PathVariable Long id) {
		var obj = service.obterPauta(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pauta cadastrarPauta(@RequestBody Pauta pauta) {
		return service.cadastrar(pauta);
	}

}
