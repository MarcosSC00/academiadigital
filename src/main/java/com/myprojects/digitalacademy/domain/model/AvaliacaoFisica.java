package com.myprojects.digitalacademy.domain.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="tb_avaliacoes")
public class AvaliacaoFisica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="data_avaliacao")
	private LocalDate dataAvaliacao;

	private double peso;

	private double altura;
	
	private String resultado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDate dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	
}
