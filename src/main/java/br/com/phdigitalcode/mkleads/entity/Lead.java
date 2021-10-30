package br.com.phdigitalcode.mkleads.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Lead implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(unique = true, name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "plataforma_ID")
    private Plataforma plataforma;
    @ManyToOne
    @JoinColumn(name = "nome_campanha_ID")
    private Campanha nomeCampanha;
    private LocalDate dataCaptura;
    @ManyToOne
    @JoinColumn(name = "curso_ID")
    private Curso curso;
    public Lead(Long id) {
    	this.id=id;
    }
}
