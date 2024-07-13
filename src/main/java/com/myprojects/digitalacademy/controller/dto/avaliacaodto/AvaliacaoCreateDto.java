package com.myprojects.digitalacademy.controller.dto.avaliacaodto;

import com.myprojects.digitalacademy.domain.model.AvaliacaoFisica;

public record AvaliacaoCreateDto( 
		double peso, 
		double altura) {

	public AvaliacaoCreateDto(AvaliacaoFisica model) {
		this(
				model.getPeso(),
				model.getAltura()
			);
		 
	}
	
	public AvaliacaoFisica toModel() {
		
		AvaliacaoFisica model = new AvaliacaoFisica();
		model.setPeso(peso);
		model.setAltura(altura);
		
		return model;
	}
}
