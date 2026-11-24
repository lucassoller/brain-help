package com.brain.help.entity;

import java.util.Collection;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "medico")
public class Medico implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String sobrenome;

	@Column(nullable = false, unique = true, length = 150)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private boolean google;
	
	@Column(nullable = false, length = 20)
	private String telefone;
	
	@Column(length = 36)
	private String foto;
	
	@Column(nullable = false, length = 100)
	private String especializacao;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_MEDICO"));
	}

	@Override
	public @Nullable String getPassword() {
		return this.getSenha();
	}

	@Override
	public String getUsername() {
		return this.getEmail();
	}
}
