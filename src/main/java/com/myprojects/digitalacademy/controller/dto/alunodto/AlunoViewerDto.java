package com.myprojects.digitalacademy.controller.dto.alunodto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.myprojects.digitalacademy.controller.dto.auladto.AulaCreateDto;
import com.myprojects.digitalacademy.controller.dto.avaliacaodto.AvaliacaoViewerDto;
import com.myprojects.digitalacademy.domain.model.Aluno;

public record AlunoViewerDto(
		String name, 
		String telefone, 
		String email, 
		String endereco,
		LocalDate dataDeNascimento, 
		LocalDate dataMatricula,
		List<AvaliacaoViewerDto> avaliacoes,
		List<AulaCreateDto> aulas) {
	
	public AlunoViewerDto(Aluno model) {
		this(
				model.getName(),
				model.getTelefone(),
				model.getEmail(),
				model.getEndereco(),
				model.getDataDeNascimento(),
				model.getDataMatricula(),
				model.getAvaliacoesFisicas().stream().map(AvaliacaoViewerDto::new)
					.collect(Collectors.toList()),
				model.getAulas().stream().map(AulaCreateDto::new).collect(Collectors.toList())
			);
	}

	public Aluno toModel() {
		Aluno model = new Aluno();
		
		model.setName(name);
		model.setTelefone(telefone);
		model.setEmail(email);
		model.setEndereco(endereco);
		model.setDataDeNascimento(dataDeNascimento);
		model.setDataMatricula(dataMatricula);
		model.setAvaliacoesFisicas(avaliacoes.stream().map(AvaliacaoViewerDto::toModel)
				.collect(Collectors.toList()));
		model.setAulas(aulas.stream().map(AulaCreateDto::toModel).collect(Collectors.toList()));
		
		return model;
	}
}
