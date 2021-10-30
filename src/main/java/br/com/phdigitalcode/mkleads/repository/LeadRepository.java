package br.com.phdigitalcode.mkleads.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.phdigitalcode.mkleads.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}
