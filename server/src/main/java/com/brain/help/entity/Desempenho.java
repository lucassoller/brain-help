package com.brain.help.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.brain.help.enums.AvaliacaoDesempenho;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "desempenho")
public class Desempenho{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String atividade;
	
	@Column(nullable = false)
	private int pontuacao;
	
	@Column(nullable = false)
	private LocalDateTime dataRealizacao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private AvaliacaoDesempenho avaliacaoDesempenho;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dependente_id")
	private Dependente dependente;
}
