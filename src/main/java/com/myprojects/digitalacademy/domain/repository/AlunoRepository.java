package com.myprojects.digitalacademy.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myprojects.digitalacademy.domain.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
