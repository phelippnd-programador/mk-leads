package br.com.phdigitalcode.mkleads.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(unique = true, name = "telefone_ID")
    private Telefone telefone;
}
