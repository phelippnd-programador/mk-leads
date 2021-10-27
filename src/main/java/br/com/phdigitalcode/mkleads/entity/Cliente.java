package br.com.phdigitalcode.mkleads.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String email;
    @ManyToOne
    @JoinColumn(name = "telefone_ID")
    private Telefone telefone;
}
