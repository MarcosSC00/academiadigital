package com.myprojects.digitalacademy.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myprojects.digitalacademy.controller.dto.alunodto.AlunoViewerDto;
import com.myprojects.digitalacademy.controller.dto.auladto.AulaCreateDto;
import com.myprojects.digitalacademy.controller.dto.auladto.AulaViewerDto;
import com.myprojects.digitalacademy.controller.dto.instrutordto.InstrutorViewerDto;
import com.myprojects.digitalacademy.service.AulaService;

@RestController
@RequestMapping("/aulas")
public class AulaController {
	
	private final AulaService aulaService;
	
	public AulaController(AulaService aulaService) {
		this.aulaService = aulaService;
	}

	@GetMapping
	public ResponseEntity<List<AulaViewerDto>> getAll(){ 
		var aulas = aulaService.getAll();
		var aulasDto = aulas.stream().map(AulaViewerDto::new)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(aulasDto);
	}
	
	@GetMapping("/{aulaId}")
	public ResponseEntity<AulaViewerDto> getAulaById(@PathVariable("aulaId") Long aulaId){
		return ResponseEntity.ok(new AulaViewerDto(aulaService.getById(aulaId)));
	}
	
	@PostMapping
	public ResponseEntity<AulaCreateDto> createAula(@RequestBody AulaCreateDto aulaDto,
			@RequestParam("instrutorId") Long instrutorId){
		var aula = aulaService.create(aulaDto.toModel(), instrutorId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(aula.getId())
				.toUri();
		return ResponseEntity.created(location).body(new AulaCreateDto(aula));
	}
	
	@DeleteMapping("/{aulaId}")
	public ResponseEntity<String> deleteAula(@PathVariable("aulaId") Long aulaId){
		aulaService.delete(aulaId);
		return new ResponseEntity<>("Aula deletado com sucesso.", HttpStatus.OK);
	}
	
	@PutMapping("/{aulaId}")
	public ResponseEntity<AulaCreateDto> upadteAula(@PathVariable("aulaId") Long aulaId,
			@RequestParam("instrutorId") Long instrutorId,
			@RequestBody AulaCreateDto aulaDto){
		var aulaUpdated = aulaService.update(aulaId, instrutorId, aulaDto.toModel());
		return ResponseEntity.ok(new AulaCreateDto(aulaUpdated));
	}
	
	@GetMapping("/buscar-alunos/{aulaId}")
	public ResponseEntity<List<AlunoViewerDto>> findAlunos(@PathVariable("aulaId") Long aulaId){
		var alunos = aulaService.getAlunos(aulaId);
		var alunosDto = alunos.stream().map(AlunoViewerDto::new).collect(Collectors.toList());
		return ResponseEntity.ok(alunosDto);
	}
	
	@GetMapping("buscar-instrutor/{aulaId}")
	public ResponseEntity<InstrutorViewerDto> findInstrutor(@PathVariable("aulaId") Long aulaId){
		return ResponseEntity.ok(new InstrutorViewerDto(aulaService.getInstrutor(aulaId)));
	}
}
