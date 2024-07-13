package com.myprojects.digitalacademy.controller.dto.instrutordto;

import java.util.List;

import com.myprojects.digitalacademy.domain.model.Instrutor;

public record InstrutorCreateDto(
		String name, 
		String telefone, 
		String email, 
		List<String> especialidades
		) {

	public InstrutorCreateDto(Instrutor model) {
		this(
				model.getName(),
				model.getTelefone(), 
				model.getEmail(), 
				model.getEspecialidades()
			);
	}

	public Instrutor toModel() {

		Instrutor model = new Instrutor();
		model.setName(name);
		model.setTelefone(telefone);
		model.setEmail(email);
		model.setEspecialidades(especialidades);

		return model;
	}
}
