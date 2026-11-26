package com.brain.help.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import com.brain.help.enums.StatusTarefa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "tarefa")
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String tarefa;
	
	@Column(nullable = false, length = 2000)
	private String descricao;
	
	@Column(nullable = false)
	private LocalDateTime dataCriacao;

	private LocalDateTime dataFinalizacao;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private StatusTarefa statusTarefa;

	@ManyToOne
	@JoinColumn(name = "dependente_id")
	private Dependente dependente;
}
