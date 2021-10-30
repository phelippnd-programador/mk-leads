package br.com.phdigitalcode.mkleads.service;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phdigitalcode.mkleads.entity.Campanha;
import br.com.phdigitalcode.mkleads.entity.Cliente;
import br.com.phdigitalcode.mkleads.entity.Curso;
import br.com.phdigitalcode.mkleads.entity.Funcionario;
import br.com.phdigitalcode.mkleads.entity.Lead;
import br.com.phdigitalcode.mkleads.entity.Plataforma;
import br.com.phdigitalcode.mkleads.entity.Telefone;
import br.com.phdigitalcode.mkleads.repository.CursoRepository;
import br.com.phdigitalcode.mkleads.repository.LeadRepository;
import br.com.phdigitalcode.mkleads.repository.PlataformaRepository;
@Service
public class LeadService {
	private LeadRepository leadRepository;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private PlataformaService plataformaService;
	
	public LeadService(PlataformaService plataformaService,CursoService cursoService){
		this.cursoService=cursoService;
		this.plataformaService=plataformaService;
	}
	
	public Set carregar(File fileXls) throws IOException {
		Optional<List<Curso>>cursosDisponiveis = cursoService.findAll();
		Optional<List<Plataforma>>plataformaDisponiveis = plataformaService.findAll();
		Set leads = new HashSet();
		Workbook workbook = WorkbookFactory.create(fileXls);
		Sheet sheetAt = workbook.getSheetAt(0);
		int rowFinal = sheetAt.getLastRowNum();
		for (int i = sheetAt.getFirstRowNum()+1; i < rowFinal; i++) {
			Row row = sheetAt.getRow(i);
			Lead lead = new Lead();
			lead.setNomeCampanha(new Campanha(row.getCell(0).getStringCellValue()));	
			lead.setDataCaptura(
					row.getCell(1).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			lead.setPlataforma(plataformaService.selectPlataforma(row.getCell(2).getStringCellValue(),plataformaDisponiveis));
			lead.setCurso(cursoService.selectCurso(row.getCell(3).getStringCellValue(),cursosDisponiveis));
			Cliente cliente = new Cliente();
			cliente.setEmail(row.getCell(4).getStringCellValue());
			cliente.setNome(row.getCell(5).getStringCellValue().replace("p:", ""));
			Telefone telefone = new Telefone(row.getCell(6).getStringCellValue());
			cliente.setTelefone(telefone);
			lead.setCliente(cliente);
			leads.add(lead);
		}
		workbook.close();
		return new HashSet(leadRepository.saveAllAndFlush(leads));
		
	}

	public Map<Funcionario, Set<Lead>> distribuir(List<Lead> leads, List<Funcionario> funcionarios) {
		Map<Funcionario, Set<Lead>> result = new HashMap<Funcionario, Set<Lead>>();
		int prox = 0;
		int maxLead = leads.size();
		int maxFunc = funcionarios.size();
		int divisao = 0;
		int resto =0;
		if(maxFunc<maxLead) {
		divisao = maxLead / maxFunc;
		resto = maxLead % maxFunc;
		}
		else {
			divisao =  maxFunc/maxLead;
			resto = maxFunc%maxLead ;
		}
		for (int i = 1; i <= maxFunc && prox < maxLead; i++) {
			result.put(funcionarios.get(i-1),new HashSet<Lead>(leads.subList(prox, i * divisao)));
			prox = i * divisao;			
		}
		for(int i = 0 ;i<resto && prox<leads.size();i++) {
			Set<Lead> set = result.get(funcionarios.get(i));
			Set<Lead> newLead = new HashSet<Lead>();
			newLead.add(leads.get(prox));
			newLead.addAll(set);
			result.replace(funcionarios.get(i), set, newLead);
			prox++;
		}
		
		return result;

	}
}
