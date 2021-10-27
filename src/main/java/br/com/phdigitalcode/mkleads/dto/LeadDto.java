package br.com.phdigitalcode.mkleads.dto;

import br.com.phdigitalcode.mkleads.entity.Status;
import br.com.phdigitalcode.mkleads.entity.Telefone;
import lombok.Data;

@Data
public class LeadDto {
    private Long id;
    private String nome;
    private Telefone telefone;
    private Status status;
}
