package br.com.phdigitalcode.mkleads.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import br.com.phdigitalcode.mkleads.entity.Funcionario;
import br.com.phdigitalcode.mkleads.entity.Lead;

class LeadServiceTest {
    private String diretorio = "C:/Users/thiar/Documents/LEADS_EXTRUTURA.xlsx";
    private List<Lead>leads;
    private List<Funcionario> funcionarios;
    private final LeadService service = new LeadService();
    public LeadServiceTest() {
    	leads = new ArrayList<Lead>();
    	funcionarios = new ArrayList<Funcionario>();
    	
    }
    @Test
    public void teste() throws IOException {
        
        Set carregar = service.carregar(new File(diretorio));
        assertEquals(6, carregar.size());
    }
    @Test
    public void deve_Retornar3_QuandoInserirUmaListaIgual() {
    	for(long i=0;i<3;i++) {
    		leads.add(new Lead(i));	
    	}
    	for(long i=0;i<3;i++) {
    		funcionarios.add(new Funcionario(i));
    	}
    	
    }
    @Test
    public void deve_Retornar10_QuandoInserirUmaListaIgual() {
    	for(long i=0;i<4;i++) {
    		leads.add(new Lead(i));	
    	}
    	for(long i=0;i<3;i++) {
    		funcionarios.add(new Funcionario(i));
    	}
    	 Map<Funcionario, Set<Lead>> distribuir = service.distribuir(leads, funcionarios);
    	 distribuir.keySet();
    }
}