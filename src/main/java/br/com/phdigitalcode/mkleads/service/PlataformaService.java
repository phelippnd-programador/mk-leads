package br.com.phdigitalcode.mkleads.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phdigitalcode.mkleads.entity.Plataforma;
import br.com.phdigitalcode.mkleads.repository.PlataformaRepository;

@Service
public class PlataformaService {
	@Autowired
	private PlataformaRepository plataformaRepository;
	
	public PlataformaService(PlataformaRepository plataformaRepository){
		this.plataformaRepository=plataformaRepository;
	}
	public Plataforma selectPlataforma(String stringCellValue, Optional<List<Plataforma>> plataformaDisponiveis) {
		Plataforma plataforma = new Plataforma(stringCellValue);
		if (plataformaDisponiveis.isPresent()) {
			List<Plataforma> cursosFilter = plataformaDisponiveis.get()
					.stream()
					.filter(cursoAux -> cursoAux.getNome().equals(stringCellValue)).collect(Collectors.toList());
			plataforma = cursosFilter.size() > 0 ? cursosFilter.get(0) : plataforma;
		}
		return plataforma;
	}

	public Optional<List<Plataforma>> findAll() {
		 List<Plataforma> findAll = plataformaRepository.findAll();
		 return findAll.size()>0?Optional.of(findAll):Optional.empty();
	}

}
