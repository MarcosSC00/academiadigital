package com.myprojects.digitalacademy.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name="tb_instrutor")
public class Instrutor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String telefone;
	
	private String email;
	
	private List<String> especialidades;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "instrutor")
	private List<Aula> aulas;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<String> especialidades) {
		this.especialidades = especialidades;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

}
