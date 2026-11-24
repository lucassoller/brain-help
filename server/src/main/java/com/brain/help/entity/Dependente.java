package com.brain.help.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import com.brain.help.enums.AvaliacaoDesempenho;
import com.brain.help.enums.EstagioAlzheimer;
import com.brain.help.enums.Sexo;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "dependente")
public class Dependente implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, length = 100)
	private String sobrenome;

	@Column(nullable = false, unique = true, length = 150)
	private String email;

	@Column(nullable = false, length = 20)
	private String telefone;

	@Column(nullable = false)
	private String senhaHash;

	@Column(nullable = false)
	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private Sexo sexo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private EstagioAlzheimer estagioAlzheimer;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private AvaliacaoDesempenho desempenhoAtual;

	@Lob
	private String foto;

	@Column(length = 200)
	private String chaveSeguranca;

	private LocalDate dataDiagnostico;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;

	@Column(nullable = false)
	private boolean google;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_DEPENDENTE"));
	}

	@Override
	public @Nullable String getPassword() {
		return this.getSenhaHash();
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}
}