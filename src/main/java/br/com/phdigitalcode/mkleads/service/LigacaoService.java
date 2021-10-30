package br.com.phdigitalcode.mkleads.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.phdigitalcode.mkleads.entity.Funcionario;
import br.com.phdigitalcode.mkleads.entity.Lead;
import br.com.phdigitalcode.mkleads.entity.Ligacao;
import br.com.phdigitalcode.mkleads.entity.Status;
import br.com.phdigitalcode.mkleads.repository.LigacaoRepository;

@Service
public class LigacaoService {
	private StatusService statusService;
	private LigacaoRepository ligacaoRepository;
	public boolean geraLista() {
		
		return false;
	}
	public void geraListaInicial(Map<Funcionario,Set<Lead>> leadsSeparado) {
		leadsSeparado.entrySet().forEach(key->{
			geraListaInicial(key.getKey(),leadsSeparado.get(key.getKey()));
		});
	}
	public boolean geraListaInicial(Funcionario funcionario, Set<Lead> leads) {
		Set<Ligacao> ligacoes = new HashSet<Ligacao>();
		leads.forEach(lead -> {
			Ligacao ligacao = new Ligacao();
			ligacao.setLead(lead);
			ligacao.setFuncionario(funcionario);
			ligacao.setStatus(statusService.statusInicial());
			ligacao.setDescricao("");
			ligacoes.add(ligacao);

		});
		List<Ligacao> saveAll = ligacaoRepository.saveAll(ligacoes);
		return saveAll.size()>0;
	}
	public void captura() {
		int limit = 10;
		Status status ;
		ligacaoRepository.findAll(limit,status);
	}
	
	
}
