package br.com.phdigitalcode.mkleads.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Ligacao implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "lead_id")
    private Lead lead;
    @OneToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
    private String descricao;
}