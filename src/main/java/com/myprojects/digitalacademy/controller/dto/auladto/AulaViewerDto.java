package com.myprojects.digitalacademy.controller.dto.auladto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.myprojects.digitalacademy.controller.dto.alunodto.AlunoViewerDto;
import com.myprojects.digitalacademy.controller.dto.instrutordto.InstrutorViewerDto;
import com.myprojects.digitalacademy.domain.model.Aula;

public record AulaViewerDto(
		String name,
		LocalDate horario, 
		double duaracao,
		InstrutorViewerDto instrutor,
		List<AlunoViewerDto> alunos) {
	
	public AulaViewerDto(Aula model) {
		this(
				model.getName(),
				model.getHorario(),
				model.getDuracao(),
				new InstrutorViewerDto(model.getInstrutor()),
				model.getAlunos().stream().map(AlunoViewerDto::new).collect(Collectors.toList())
			);
	}
	
	public Aula toModel() {
		Aula model = new Aula();

		model.setName(name);
		model.setHorario(horario);
		model.setDuracao(duaracao);
		model.setInstrutor(instrutor.toModel());
		
		return model;
	}

}
