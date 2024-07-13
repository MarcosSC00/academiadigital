package com.myprojects.digitalacademy.service;

import java.util.List;

import com.myprojects.digitalacademy.domain.model.Aula;
import com.myprojects.digitalacademy.domain.model.Instrutor;

public interface InstrutorService {

	Instrutor create(Instrutor instrutor);

	Instrutor update(Long instrutorId, Instrutor instrutor);

	void delete(Long instrutorId);

	Instrutor getById(Long instrutorId);

	List<Instrutor> getAll();

	void addAula(Long aulaId, Long instrutorId);

	List<Aula> getAulas(Long instrutorId);
}
