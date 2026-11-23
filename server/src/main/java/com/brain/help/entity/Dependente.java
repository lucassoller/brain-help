package com.brain.help.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import jakarta.persistence.*;
import com.brain.help.enums.AvaliacaoDesempenho;
import com.brain.help.enums.EstagioAlzheimer;
import com.brain.help.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "dependente")
public class Dependente extends Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String sobrenome;
	
	@Column(nullable = false, unique = true, length = 254)
	private String email;
	
	@Column(nullable = false, length = 20)
	private String telefone;
	
	@Column(nullable = false, length = 60)
	private String senha;
	
	@Column(nullable = false)
	private int idade;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Sexo sexo;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EstagioAlzheimer estagioAlzheimer;

	@Enumerated(EnumType.STRING)
	private AvaliacaoDesempenho desempenhoAtual;
	
	@Lob
	@Column(length = 2000)
	private String foto;
	
	@Column(length = 200)
	private String chaveSeguranca;
	
	private LocalDate dataDiagnostico;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Endereco> enderecos;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Vinculo> vinculos;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Medicamento> medicamentos;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Fotografia> fotografias;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Musica> musicas;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Tarefa> tarefas;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "dependente")
	private List<Desempenho> desempenhos;
	
	@Column(nullable = false)
	private boolean google;

	@Override
	public String getRole() {
		return "ROLE_DIAGNOSTICADO";
	}
}
