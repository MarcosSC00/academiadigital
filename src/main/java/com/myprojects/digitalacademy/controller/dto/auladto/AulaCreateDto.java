package com.myprojects.digitalacademy.controller.dto.auladto;

import java.time.LocalDate;

import com.myprojects.digitalacademy.domain.model.Aula;

public record AulaCreateDto(
		String name,
		LocalDate horario, 
		double duaracao) {
	
	public AulaCreateDto(Aula model) {
		this(
				model.getName(),
				model.getHorario(),
				model.getDuracao()
			);
	}
	
	public Aula toModel() {
		Aula model = new Aula();

		model.setName(name);
		model.setHorario(horario);
		model.setDuracao(duaracao);
		
		return model;
	}

}
