package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Atividade;
import com.example.demo.model.enumerated.TipoAtividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer>{
	List<Atividade> findByTipoAtividade(TipoAtividade tipoAtividade);
}
