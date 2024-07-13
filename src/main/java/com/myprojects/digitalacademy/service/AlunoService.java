package com.myprojects.digitalacademy.service;

import java.util.List;

import com.myprojects.digitalacademy.domain.model.Aluno;
import com.myprojects.digitalacademy.domain.model.Aula;
import com.myprojects.digitalacademy.domain.model.AvaliacaoFisica;

public interface AlunoService {

	Aluno create(Aluno aluno);

	Aluno upadte(Long id, Aluno aluno);
	
	void delete(Long alunoId);

	Aluno getById(Long alunoId);

	AvaliacaoFisica addAvaliacaoFisica(Long alunoId, AvaliacaoFisica avaliacao);

	void addAula(Long alunoId, Long aulaId);

	List<Aula> getAulas(Long alunoId);

	List<Aluno> getAll();

	List<AvaliacaoFisica> getAvaliacoesFisica(Long id);

}
