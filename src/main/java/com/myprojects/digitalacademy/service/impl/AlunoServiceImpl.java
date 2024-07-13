package com.myprojects.digitalacademy.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myprojects.digitalacademy.domain.model.Aluno;
import com.myprojects.digitalacademy.domain.model.Aula;
import com.myprojects.digitalacademy.domain.model.AvaliacaoFisica;
import com.myprojects.digitalacademy.domain.repository.AlunoRepository;
import com.myprojects.digitalacademy.domain.repository.AulaRepository;
import com.myprojects.digitalacademy.domain.repository.AvaliacaoFisicaRepository;
import com.myprojects.digitalacademy.service.AlunoService;
import com.myprojects.digitalacademy.service.exception.BusinessException;
import com.myprojects.digitalacademy.service.exception.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class AlunoServiceImpl implements AlunoService {

	private final AlunoRepository alunoRepository;
	private final AulaRepository aulaRepository;
	private final AvaliacaoFisicaRepository avaliacaoRep;

	public AlunoServiceImpl(AlunoRepository alunoRepository,
			AulaRepository aulaRepository, AvaliacaoFisicaRepository avaliacaoRep) {
		this.alunoRepository = alunoRepository;
		this.aulaRepository = aulaRepository;
		this.avaliacaoRep = avaliacaoRep;
	}

	@Override
	public Aluno create(Aluno alunoToCreate) {
		Optional.ofNullable(alunoToCreate).orElseThrow(() -> new BusinessException("O aluno não pode ser nulo"));
		
		if(alunoToCreate.getName() == null || alunoToCreate.getName().isEmpty()) {
			throw new BusinessException("O nome do aluno deve ser preenchido");
		}
		
		return alunoRepository.save(alunoToCreate);
	}

	@Override
	public Aluno upadte(Long id, Aluno alunoToUpdate) {
		Aluno newAluno = this.getById(id);

		newAluno.setName(alunoToUpdate.getName());
		newAluno.setEmail(alunoToUpdate.getEmail());
		newAluno.setTelefone(alunoToUpdate.getTelefone());
		newAluno.setEndereco(alunoToUpdate.getEndereco());
		newAluno.setDataDeNascimento(alunoToUpdate.getDataDeNascimento());
		newAluno.setDataMatricula(alunoToUpdate.getDataMatricula());
		newAluno = alunoRepository.saveAndFlush(newAluno);

		return newAluno;
	}

	@Override
	public void delete(Long alunoId) {
		Aluno aluno = getById(alunoId);
		alunoRepository.delete(aluno);
	}

	@Override
	public Aluno getById(Long alunoId) {
		return alunoRepository.findById(alunoId)
				.orElseThrow(() -> new NotFoundException("aluno não encontrado"));
	}

	@Override
	public AvaliacaoFisica addAvaliacaoFisica(Long alunoId, AvaliacaoFisica avaliacao) {
		Aluno aluno = alunoRepository.findById(alunoId).get();
		avaliacao.setResultado(resultAvaliacao(avaliacao.getPeso(), avaliacao.getAltura()));
		aluno.getAvaliacoesFisicas().add(Optional.ofNullable(avaliacao)
				.orElseThrow(() -> new BusinessException("A avaliação está vazia.")));
		AvaliacaoFisica av = avaliacaoRep.save(avaliacao);
		aluno = alunoRepository.save(aluno);
		return av;
	}

	
	@Override
	@Transactional
	public void addAula(Long alunoId, Long aulaId) {
		Aluno aluno =getById(alunoId);
		Aula aula = aulaRepository.findById(aulaId)
				.orElseThrow(() -> new NotFoundException("Aula não encontrada."));
		boolean aulaCadastrada = aluno.getAulas().stream().anyMatch(a -> a.getId().equals(aulaId));
		if(aulaCadastrada) {
			throw new BusinessException("aula já cadastrada.");
		}
		
		aula.getAlunos().add(aluno);
		aluno.getAulas().add(aula);
		
		alunoRepository.save(aluno);
		aulaRepository.saveAndFlush(aula);
	}

	@Override
	public List<Aula> getAulas(Long alunoId) {
		Aluno aluno = getById(alunoId);
		var aulasInAluno = Optional.ofNullable(aluno.getAulas())
				.orElseThrow(() -> new BusinessException("nenhuma aula encontrada"));
		if(aulasInAluno.isEmpty()) {
			throw new BusinessException("nenhuma aula encontrada");
		}
		
		return aulasInAluno;
	}

	@Override
	public List<Aluno> getAll() {
		return Optional.ofNullable(alunoRepository.findAll())
				.orElseThrow(() -> new BusinessException("Nenhum aluno encontrado!"));
	}

	@Override
	public List<AvaliacaoFisica> getAvaliacoesFisica(Long alunoId) {
		Aluno aluno = getById(alunoId);
		var avaliacoesInAluno = Optional.ofNullable(aluno.getAvaliacoesFisicas())
				.orElseThrow(() -> new BusinessException("nenhuma avaliação encontrada."));
		if(avaliacoesInAluno.isEmpty()) {
			throw new BusinessException("nenhuma avaliação encontrada.");
		}
		return avaliacoesInAluno;
	}
	
	public String resultAvaliacao(double peso, double altura) {
		double imc = peso/(altura * altura);
		String result = "";
		
		if(imc < 18.5) {
			result = "Seu IMC está abaixo do normal.Considere as seguintes atividades: "
					+ "Aumentar a ingestão calorica;Fazer refeições regulares;"
					+ "Incluir proteínas em todas as refeições;Consultar um nutricionista"
					+ "%nRealizar exercícios de força";
		}else if(imc >= 18.5 && imc <= 24.9) {
			result = String.format("Seu IMC está normal."
					+ "Mantenha sua dieta balanceada;"
					+ "Pratique exercícios regularmente;");
		}else if(imc >= 25 && imc <= 29.9){
			result = String.format("Seu IMC está acima do normal.Considere as seguintes atividades:"
					+ "Reduza a ingestão calórica;"
					+ "Aumente a atividade física;"
					+ "Aumente o consumo de fibras;"
					+ "Estabeleça metas de perda de peso;"
					+ "Consulte um profissional de saúde;");
		}else {
			result = String.format("Seu IMC está muito acima do normal.Considere as seguintes atividades:"
					+ "Siga uma dieta supervisionada;"
					+ "Intensifique a atividade física;"
					+ "Considere a intervenção médica;");
		}
		return result;
	}

}