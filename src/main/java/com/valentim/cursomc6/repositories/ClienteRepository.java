package com.valentim.cursomc6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.valentim.cursomc6.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
	
	//validar email 
	@Transactional(readOnly=true)
	Cliente findByEmail(String email);

}
