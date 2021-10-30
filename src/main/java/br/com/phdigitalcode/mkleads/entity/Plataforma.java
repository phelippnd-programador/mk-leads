package br.com.phdigitalcode.mkleads.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "plataforma")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plataforma implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    private String nome;
    public Plataforma(String nome) {
    	this.nome=nome;
    }
}