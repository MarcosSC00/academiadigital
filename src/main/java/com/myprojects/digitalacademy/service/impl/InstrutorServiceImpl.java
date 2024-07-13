package com.myprojects.digitalacademy.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.myprojects.digitalacademy.domain.model.Aula;
import com.myprojects.digitalacademy.domain.model.Instrutor;
import com.myprojects.digitalacademy.domain.repository.AulaRepository;
import com.myprojects.digitalacademy.domain.repository.InstrutorRepository;
import com.myprojects.digitalacademy.service.InstrutorService;
import com.myprojects.digitalacademy.service.exception.BusinessException;
import com.myprojects.digitalacademy.service.exception.NotFoundException;

@Service
public class InstrutorServiceImpl implements InstrutorService{
	
	private final InstrutorRepository instRepository;
	private final AulaRepository aulaRepository;
	
	public InstrutorServiceImpl(InstrutorRepository instRepository, AulaRepository aulaRepository) {
		this.instRepository = instRepository;
		this.aulaRepository = aulaRepository;
	}

	@Override
	public Instrutor create(Instrutor instrutor) {
		Optional.ofNullable(instrutor).orElseThrow(() -> new NotFoundException("o instrutor não pode ser nulo."));
		if(instrutor.getName() == null || instrutor.getName().isEmpty()) {
			throw new BusinessException("informe o nome do instrutor.");
		}
		return instRepository.save(instrutor);
	}

	@Override
	public Instrutor update(Long instrutorId, Instrutor instrutorToUpdate) {
		Instrutor newInstrutor = getById(instrutorId);
		newInstrutor.setEmail(instrutorToUpdate.getEmail());
		newInstrutor.setEspecialidades(instrutorToUpdate.getEspecialidades());
		newInstrutor.setName(instrutorToUpdate.getName());
		newInstrutor.setTelefone(instrutorToUpdate.getTelefone());
		instRepository.saveAndFlush(newInstrutor);
		
		return newInstrutor;
	}

	@Override
	public void delete(Long instrutorId) {
		Instrutor instrutor = getById(instrutorId);
		instRepository.delete(instrutor);
	}

	@Override
	public Instrutor getById(Long instrutorId) {
		return instRepository.findById(instrutorId)
				.orElseThrow(() -> new NotFoundException("instrutor não encontrado"));
	}

	@Override
	public List<Instrutor> getAll() {
		return Optional.ofNullable(instRepository.findAll())
				.orElseThrow(() -> new NotFoundException("nenhum instrutor encontrado."));
	}

	@Override
	public void addAula(Long aulaId, Long instrutorId) {
		Aula aula = aulaRepository.findById(aulaId)
				.orElseThrow(() -> new NotFoundException("aula não encontrada."));
		Instrutor instrutor = getById(instrutorId);
		
		instrutor.getAulas().add(aula);
		aula.setInstrutor(instrutor);

		aulaRepository.saveAndFlush(aula);
		instRepository.save(instrutor);
	}

	@Override
	public List<Aula> getAulas(Long instrutorId) {
		var aulas = getById(instrutorId).getAulas().stream().collect(Collectors.toList());
		if(aulas.isEmpty() || aulas == null) {
			throw new BusinessException("nenhuma aula cadastrada.");
		}
		return aulas;
	}

}
