package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Fotografia;
import com.example.demo.model.Lembranca;
import com.example.demo.model.Musica;
import com.example.demo.model.Vinculo;

public interface LembrancaRepository extends JpaRepository<Lembranca, Integer>{
	List<Lembranca> findByFotografia(Fotografia fotografia);
	List<Lembranca> findByMusica(Musica musica);
	List<Lembranca> findByVinculo(Vinculo vinculo);
	
	List<Lembranca> findByFotografiaAndTituloContaining(Fotografia fotografia, String titulo);
	List<Lembranca> findByMusicaAndTituloContaining(Musica musica, String titulo);
	List<Lembranca> findByVinculoAndTituloContaining(Vinculo vinculo, String titulo);
}