package br.com.phdigitalcode.mkleads.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "telefone")
@Entity
public class Telefone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ddd;
    private String numero;

    public Telefone(String numero) {
        this.numero = numero;
    }
}