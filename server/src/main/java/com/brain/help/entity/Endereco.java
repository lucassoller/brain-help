package com.brain.help.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String logradouro;
	
	@Column(nullable = false, length = 100)
	private String cidade;
	
	@Column(nullable = false, length = 100)
	private String estado;
	
	@Column(nullable = false)
	private int numero;
	
	@Column(nullable = false, length = 100)
	private String bairro;
	
	@Column(length = 9)
	private String cep;
	
	@Lob
	@Column(length = 2000)
	private String foto;
	
	@Column(length = 100)
	private String titulo;

	@Column(nullable = false)
	private boolean principal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dependente_id", nullable = false)
	private Dependente dependente;
}