package com.brain.help.repository;

import com.brain.help.entity.RecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RecuperacaoSenhaRepository extends JpaRepository<RecuperacaoSenha, Long> {

    Optional<RecuperacaoSenha> findByToken(String token);

    void deleteByExpiracaoBefore(LocalDateTime limite);
}