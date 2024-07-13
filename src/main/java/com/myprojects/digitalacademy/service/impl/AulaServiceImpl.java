package com.myprojects.digitalacademy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myprojects.digitalacademy.domain.model.Aluno;
import com.myprojects.digitalacademy.domain.model.Aula;
import com.myprojects.digitalacademy.domain.model.Instrutor;
import com.myprojects.digitalacademy.domain.repository.AulaRepository;
import com.myprojects.digitalacademy.domain.repository.InstrutorRepository;
import com.myprojects.digitalacademy.service.AulaService;
import com.myprojects.digitalacademy.service.exception.BusinessException;
import com.myprojects.digitalacademy.service.exception.NotFoundException;

@Service
public class AulaServiceImpl implements AulaService{
	
	private final AulaRepository aulaRepository;
	private final InstrutorRepository instRepository;

	public AulaServiceImpl(AulaRepository aulaRepository, InstrutorRepository instRepository) {
		this.aulaRepository = aulaRepository;
		this.instRepository = instRepository;
	}
	
	@Override
	public Aula create(Aula aula, Long idInstrutor) {
		Optional.ofNullable(aula).
			orElseThrow(() -> new BusinessException("A aula não pode ser nula."));
		
		var instrutor = instRepository.findById(idInstrutor)
				.orElseThrow(() -> new NotFoundException("instrutor não encontrado."));
		aula.setInstrutor(instrutor);

		return aulaRepository.save(aula);
	}

	@Override
	public Aula update(Long aulaId, Long instrutorId, Aula aulaToUpdate) {
		Aula aula = getById(aulaId);
		Instrutor instrutor = instRepository.findById(instrutorId)
				.orElseThrow(() -> new BusinessException("instrutor não encontrado"));
		
		
		aula.setDuracao(aulaToUpdate.getDuracao());
		aula.setHorario(aulaToUpdate.getHorario());
		aula.setInstrutor(instrutor);
		aula.setName(aulaToUpdate.getName());
		
		return aulaRepository.saveAndFlush(aula);
	}

	@Override
	public void delete(Long aulaId) {
		aulaRepository.delete(getById(aulaId));
	}

	@Override
	public Aula getById(Long aulaId) {
		return aulaRepository.findById(aulaId)
				.orElseThrow(() -> new NotFoundException("aula não encontrada"));
	}

	@Override
	public List<Aula> getAll() {
		return Optional.ofNullable(aulaRepository.findAll())
				.orElseThrow(() -> new NotFoundException("Nenhuma aula encontrada."));
	}

	@Override
	public Instrutor getInstrutor(Long id){
		return getById(id).getInstrutor();
	}

	@Override
	public List<Aluno> getAlunos(Long id) {
		var alunos = getById(id).getAlunos();
		if(alunos.isEmpty()) {
			throw new BusinessException("nenhum aluno cadastrado.");
		}
		return Optional.ofNullable(alunos)
				.orElseThrow(() -> new BusinessException("nenhum aluno cadastrado."));
	}

}
