package com.brain.help.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brain.help.entity.Dependente;
import com.brain.help.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	
	List<Endereco> findByDiagnosticado(Dependente dependente);

}
