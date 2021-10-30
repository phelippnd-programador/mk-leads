package br.com.phdigitalcode.mkleads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phdigitalcode.mkleads.entity.Curso;
import br.com.phdigitalcode.mkleads.entity.Lead;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
