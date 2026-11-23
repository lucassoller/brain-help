package com.brain.help.entity;

import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.*;
import com.brain.help.enums.Frequencia;
import com.brain.help.enums.Medida;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "medicamento")
public class Medicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 200)
	private String nomeMedicamento;
	
	@Column(length = 1000)
	private String funcao;
	
	@Column(length = 2000)
	private String contraIndicacoes;
	
	@Column(length = 2000)
	private String efeitosColaterais;
	
	@Column(nullable = false)
	private int quantidade;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Medida medida;
	
	private LocalDateTime horario;

	@Enumerated(EnumType.STRING)
	private Frequencia frequencia;
	
	@Lob
	@Column(length = 2000)
	private String foto;

	@ManyToOne
	@JoinColumn(name = "dependente_id")
	private Dependente dependente;
}