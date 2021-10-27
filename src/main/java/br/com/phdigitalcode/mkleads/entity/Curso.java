package br.com.phdigitalcode.mkleads.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
public class Curso implements Serializable {
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    public Curso(String nome) {
        this.nome=nome;
    }
}