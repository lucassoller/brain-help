package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Arquivo;
import com.example.demo.model.Atividade;
import com.example.demo.model.enumerated.TipoAtividade;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer>{
}
