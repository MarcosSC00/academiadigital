package com.myprojects.digitalacademy.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myprojects.digitalacademy.controller.dto.auladto.AulaCreateDto;
import com.myprojects.digitalacademy.controller.dto.instrutordto.InstrutorCreateDto;
import com.myprojects.digitalacademy.controller.dto.instrutordto.InstrutorViewerDto;
import com.myprojects.digitalacademy.domain.model.Instrutor;
import com.myprojects.digitalacademy.service.InstrutorService;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {
	
	private final InstrutorService service;
	
	public InstrutorController(InstrutorService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<InstrutorViewerDto>> getAll(){
		var instrutores = service.getAll();
		var instrutoresDto = instrutores.stream().map(InstrutorViewerDto::new).collect(Collectors.toList());
		return ResponseEntity.ok(instrutoresDto);
	}
	
	@GetMapping("/{instrutorId}")
	public ResponseEntity<InstrutorViewerDto> getById(@PathVariable("instrutorId") Long instrutorId){
		return ResponseEntity.ok(new InstrutorViewerDto(service.getById(instrutorId)));
	}
	
	@PostMapping
	public ResponseEntity<InstrutorCreateDto> create(@RequestBody InstrutorCreateDto instrutorDto){
		Instrutor instrutor = service.create(instrutorDto.toModel());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(instrutor.getId()).toUri();
		return ResponseEntity.created(location).body(new InstrutorCreateDto(instrutor));
	}
	
	@PutMapping("/atualizar/{instrutorId}")
	public ResponseEntity<InstrutorViewerDto> update(@PathVariable("instrutorId") Long id, @RequestBody InstrutorCreateDto instrutorDto){
		var instrutorUpadated = service.update(id, instrutorDto.toModel());
		return ResponseEntity.ok(new InstrutorViewerDto(instrutorUpadated));
	}
	
	@GetMapping("/buscar-aulas/{instrutorId}")
	public ResponseEntity<List<AulaCreateDto>> getAulas(@PathVariable("instrutorId") Long instrutorId){
		var aulas = service.getAulas(instrutorId);
		var aulasDto = aulas.stream().map(AulaCreateDto::new).collect(Collectors.toList());
		return ResponseEntity.ok(aulasDto);
	}
	
	@PostMapping("/adicionar-aula/{instrutorId}")
	public ResponseEntity<String> addAula(@PathVariable("instrutorId")Long instrutorId,
			@RequestParam("aulaId") Long aulaId){
		service.addAula(aulaId, instrutorId);
		return ResponseEntity.ok("aula adiciona com sucesso.");
	}
	
	@DeleteMapping("{instrutorId}")
	public ResponseEntity<String> delete(@PathVariable("instrutorId") Long instrutorId){
		service.delete(instrutorId);
		return ResponseEntity.ok("instrutor deletado com sucesso.");
	}
}
