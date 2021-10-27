package br.com.phdigitalcode.mkleads.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
public class Campanha implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    public Campanha(String nome) {
        this.nome=nome;
    }
}