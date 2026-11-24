package com.brain.help.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "musica")
public class Musica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String nomeMusica;
	
	@Column(nullable = false, length = 200)
	private String cantor;
	
	@Column(length = 2000)
	private String lembrancas;
	
	@Column(nullable = false)
	private String musica;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dependente_id")
	private Dependente dependente;
}