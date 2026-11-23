package com.brain.help.entity;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "fotografia")
public class Fotografia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String lugar;
	
	private LocalDate data;
	
	@Column(nullable = false, length = 2000)
	private String lembrancas;
	
	@Lob
	@Column(nullable = false, length = 2000)
	private String foto;

	@ManyToOne
	@JoinColumn(name = "dependente_id")
	private Dependente dependente;
}
