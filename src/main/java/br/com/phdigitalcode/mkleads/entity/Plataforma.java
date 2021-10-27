package br.com.phdigitalcode.mkleads.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "plataforma")
@Entity
public class Plataforma implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;
    public Plataforma(String nome) {

    }
}