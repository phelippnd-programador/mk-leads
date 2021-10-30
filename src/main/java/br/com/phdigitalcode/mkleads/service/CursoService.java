package br.com.phdigitalcode.mkleads.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phdigitalcode.mkleads.entity.Curso;
import br.com.phdigitalcode.mkleads.entity.Plataforma;
import br.com.phdigitalcode.mkleads.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	CursoRepository cursoRepository;
	public CursoService(CursoRepository cursoRepository) {
		this.cursoRepository=cursoRepository;
	}
	public Optional<List<Curso>> findAll() {
		List<Curso> findAll = cursoRepository.findAll();
		 return findAll.size()>0?Optional.of(findAll):Optional.empty();
		
	}
	public Curso selectCurso(String stringCellValue, Optional<List<Curso>> cursosDisponiveis) {
		Curso curso = new Curso(stringCellValue);
		if(cursosDisponiveis.isPresent()) {
			 List<Curso> cursosFilter = cursosDisponiveis.get()
			 	.stream()
			 	.filter(cursoAux -> cursoAux.getNome().equals(stringCellValue)).collect(Collectors.toList());
			curso = cursosFilter.size()>0?cursosFilter.get(0):curso;
		}
		return curso;
	}
	
}
