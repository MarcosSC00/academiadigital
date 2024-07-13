package com.myprojects.digitalacademy.domain.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity(name="tb_aula")
public class Aula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate horario;

	@ManyToOne(cascade = CascadeType.ALL)
	private Instrutor instrutor;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "aula_id",
			joinColumns = @JoinColumn(name="aula_id"),
			inverseJoinColumns = @JoinColumn(name="aluno_id")
	)
	private List<Aluno> alunos;

	private double duracao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getHorario() {
		return horario;
	}

	public void setHorario(LocalDate horario) {
		this.horario = horario;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

}
