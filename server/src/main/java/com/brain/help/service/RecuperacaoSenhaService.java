package com.brain.help.service;

import com.brain.help.dto.senha.RecuperarSenhaDTO;
import com.brain.help.dto.senha.RedefinirSenhaDTO;
import com.brain.help.entity.Dependente;
import com.brain.help.entity.Medico;
import com.brain.help.entity.RecuperacaoSenha;
import com.brain.help.exception.BusinessException;
import com.brain.help.repository.DependenteRepository;
import com.brain.help.repository.MedicoRepository;
import com.brain.help.repository.RecuperacaoSenhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecuperacaoSenhaService {

    private final RecuperacaoSenhaRepository recuperacaoSenhaRepository;
    private final MedicoRepository medicoRepository;
    private final DependenteRepository dependenteRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Value("${frontend.url}")
    private String frontendUrl;

    @Transactional
    public void solicitarRecuperacaoMedico(RecuperarSenhaDTO dto) throws IOException {
        medicoRepository.findByEmail(dto.getEmail()).ifPresent(medico -> {
            RecuperacaoSenha recuperacao = criarRegistro(medico, null);
            enviarLink(medico.getEmail(), recuperacao.getToken());
        });
    }

    @Transactional
    public void solicitarRecuperacaoDependente(RecuperarSenhaDTO dto) throws IOException {
        dependenteRepository.findByEmail(dto.getEmail()).ifPresent(dependente -> {
            RecuperacaoSenha recuperacao = criarRegistro(null, dependente);
            enviarLink(dependente.getEmail(), recuperacao.getToken());
        });
    }

    @Transactional
    public void redefinirSenha(RedefinirSenhaDTO dto) {
        if (!Objects.equals(dto.getNovaSenha(), dto.getConfirmarSenha())) {
            throw new BusinessException("As senhas não coincidem");
        }

        RecuperacaoSenha recuperacao = recuperacaoSenhaRepository.findByToken(dto.getToken())
                .orElseThrow(() -> new BusinessException("Link inválido"));

        if (recuperacao.isUsado()) {
            throw new BusinessException("Link já utilizado");
        }

        if (recuperacao.getExpiracao().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Link expirado");
        }

        String novaSenhaHash = passwordEncoder.encode(dto.getNovaSenha());

        if (recuperacao.getMedico() != null) {
            Medico medico = recuperacao.getMedico();
            medico.setSenha(novaSenhaHash);
            medicoRepository.save(medico);
        } else if (recuperacao.getDependente() != null) {
            Dependente dependente = recuperacao.getDependente();
            dependente.setSenhaHash(novaSenhaHash);
            dependenteRepository.save(dependente);
        }

        recuperacao.setUsado(true);
        recuperacaoSenhaRepository.save(recuperacao);
    }

    @Scheduled(fixedRate = 3600000) // a cada 1 hora
    @Transactional
    public void limparTokensExpirados() {
        recuperacaoSenhaRepository.deleteByExpiracaoBefore(LocalDateTime.now().minusHours(24));
    }

    private RecuperacaoSenha criarRegistro(Medico medico, Dependente dependente) {
        RecuperacaoSenha recuperacao = new RecuperacaoSenha();
        recuperacao.setToken(UUID.randomUUID().toString());
        recuperacao.setMedico(medico);
        recuperacao.setDependente(dependente);
        recuperacao.setExpiracao(LocalDateTime.now().plusMinutes(30));
        recuperacao.setUsado(false);
        return recuperacaoSenhaRepository.save(recuperacao);
    }

    private void enviarLink(String email, String token) {
        try {
            String link = frontendUrl + "/redefinir-senha?token=" + token;
            emailService.enviarEmail(email, link);
        } catch (IOException e) {
            throw new BusinessException("Não foi possível enviar o e-mail de recuperação");
        }
    }
}