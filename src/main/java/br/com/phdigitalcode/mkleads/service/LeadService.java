package br.com.phdigitalcode.mkleads.service;

import java.io.File;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import br.com.phdigitalcode.mkleads.entity.Campanha;
import br.com.phdigitalcode.mkleads.entity.Cliente;
import br.com.phdigitalcode.mkleads.entity.Curso;
import br.com.phdigitalcode.mkleads.entity.Funcionario;
import br.com.phdigitalcode.mkleads.entity.Lead;
import br.com.phdigitalcode.mkleads.entity.Plataforma;
import br.com.phdigitalcode.mkleads.entity.Telefone;

public class LeadService {
	public Set carregar(File fileXls) throws IOException {
		Set leads = new HashSet();
		Workbook workbook = WorkbookFactory.create(fileXls);
		Sheet sheetAt = workbook.getSheetAt(0);
		int rowFinal = sheetAt.getLastRowNum();
		for (int i = sheetAt.getFirstRowNum(); i < rowFinal; i++) {
			Row row = sheetAt.getRow(i);
			Lead lead = new Lead();
			lead.setDataCaptura(
					row.getCell(0).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			lead.setNomeCampanha(new Campanha(row.getCell(1).getStringCellValue()));
			lead.setPlataforma(new Plataforma(row.getCell(2).getStringCellValue()));
			lead.setCurso(new Curso(row.getCell(3).getStringCellValue()));
			Cliente cliente = new Cliente();
			cliente.setEmail(row.getCell(4).getStringCellValue());
			cliente.setNome(row.getCell(5).getStringCellValue());
			Telefone telefone = new Telefone(row.getCell(6).getStringCellValue());
			cliente.setTelefone(telefone);
			lead.setCliente(cliente);
			leads.add(lead);
		}
		return leads;
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
