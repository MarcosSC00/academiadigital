package com.myprojects.digitalacademy.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myprojects.digitalacademy.controller.dto.alunodto.AlunoCreateDto;
import com.myprojects.digitalacademy.controller.dto.alunodto.AlunoViewerDto;
import com.myprojects.digitalacademy.controller.dto.auladto.AulaCreateDto;
import com.myprojects.digitalacademy.controller.dto.avaliacaodto.AvaliacaoCreateDto;
import com.myprojects.digitalacademy.controller.dto.avaliacaodto.AvaliacaoViewerDto;
import com.myprojects.digitalacademy.domain.model.Aluno;
import com.myprojects.digitalacademy.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
	
	private AlunoService alunoService;

	public AlunoController(AlunoService alunoRep) {
		this.alunoService = alunoRep;
	}

	@GetMapping
	public ResponseEntity<List<AlunoViewerDto>> getAll(){
		var alunos = alunoService.getAll();
		var alunoDto = alunos.stream().map(AlunoViewerDto::new).collect(Collectors.toList());
		return ResponseEntity.ok(alunoDto);
	}
	
	@GetMapping("/{alunoId}")
	public ResponseEntity<AlunoViewerDto> getAlunoById(@PathVariable("alunoId") Long alunoId){
		return ResponseEntity.ok(new AlunoViewerDto(alunoService.getById(alunoId)));
	}

	@PostMapping
	public ResponseEntity<AlunoCreateDto> create(@RequestBody AlunoCreateDto alunoDto){
		var aluno = alunoService.create(alunoDto.toModel());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(aluno.getId())
				.toUri();
		return ResponseEntity.created(location).body(new AlunoCreateDto(aluno));
	}
	
	@PostMapping("/adicionar-aula/{alunoId}")
	public ResponseEntity<String> addAula(@PathVariable("alunoId") Long alunoId,
			@RequestParam("aulaId") Long aulaId ){
		alunoService.addAula(alunoId, aulaId);
		return ResponseEntity.ok("aula inserida com sucesso");
	}
	
	
	@PutMapping("/update-aluno/{alunoId}")
	public ResponseEntity<AlunoCreateDto> updateAluno(@PathVariable("alunoId") Long alunoId,
			@RequestBody AlunoCreateDto alunoDto){
		Aluno updateAluno = alunoService.upadte(alunoId, alunoDto.toModel());
		return ResponseEntity.ok(new AlunoCreateDto(updateAluno));
	}
	
	@DeleteMapping("/{alunoId}")
	public ResponseEntity<String> deleteAluno(@PathVariable("alunoId") Long alunoId){
		alunoService.delete(alunoId);
		return ResponseEntity.ok("aluno deletado com sucesso");
	}
	
	@GetMapping("/buscar-aulas/{alunoId}")
	public ResponseEntity<List<AulaCreateDto>> getAulas(@PathVariable("alunoId") Long alunoId){
		var aulas = alunoService.getAulas(alunoId);
		var aulasDto = aulas.stream().map(AulaCreateDto::new).collect(Collectors.toList());
		
		return ResponseEntity.ok(aulasDto);
	}
	
	@PostMapping("/adicionar-avaliacao/{alunoId}")
	public ResponseEntity<AvaliacaoCreateDto> addAvaliacao(@PathVariable("alunoId") Long alunoId,
			@RequestBody AvaliacaoCreateDto avaliacao){
		alunoService.addAvaliacaoFisica(alunoId, avaliacao.toModel());
		
		return ResponseEntity.ok(avaliacao);
	}
	
	@GetMapping("/buscar-avaliacoes/{alunoId}")
	public ResponseEntity<List<AvaliacaoViewerDto>> getAvaliacoes(@PathVariable("alunoId") Long alunoId){
		var avaliacoes = alunoService.getAvaliacoesFisica(alunoId);
		var avaliacoesDto = avaliacoes.stream().map(AvaliacaoViewerDto::new)
				.collect(Collectors.toList());
		
		return ResponseEntity.ok(avaliacoesDto);
	}
}