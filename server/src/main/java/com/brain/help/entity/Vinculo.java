package com.brain.help.entity;

import jakarta.persistence.*;
import com.brain.help.enums.Sexo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vinculo")
public class Vinculo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 20)
    private String telefone;
	
	@Column(nullable = false, length = 100)
	private String sobrenome;
	
	@Column(nullable = false, length = 100)
	private String vinculo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;
	
	@Column(nullable = false)
	private int idade;
		
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@Column(length = 2000)
	private String lembrancas;

	@ManyToOne
	@JoinColumn(name = "dependente_id")
	private Dependente dependente;
	
	@Lob
	@Column(length = 2000)
	private String foto;
}
