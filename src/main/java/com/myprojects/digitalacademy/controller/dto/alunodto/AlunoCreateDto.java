package com.myprojects.digitalacademy.controller.dto.alunodto;

import java.time.LocalDate;

import com.myprojects.digitalacademy.domain.model.Aluno;

public record AlunoCreateDto(
		String name, 
		String telefone, 
		String email, 
		String endereco,
		LocalDate dataDeNascimento, 
		LocalDate dataMatricula
		){

	public AlunoCreateDto (Aluno model){
		this(
				model.getName(),
				model.getTelefone(),
				model.getEmail(),
				model.getEndereco(),
				model.getDataDeNascimento(),
				model.getDataMatricula()
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
		
		return model;
	}
}
