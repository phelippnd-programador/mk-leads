package br.com.phdigitalcode.mkleads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.phdigitalcode.mkleads.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	
}
