package br.com.phdigitalcode.mkleads.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phdigitalcode.mkleads.entity.Lead;
import br.com.phdigitalcode.mkleads.entity.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {


	Optional<Plataforma> findByNome(String stringCellValue);
}
