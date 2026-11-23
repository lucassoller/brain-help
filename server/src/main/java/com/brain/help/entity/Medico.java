package com.brain.help.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "medico")
public class Medico extends Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(nullable = false, length = 100)
	private String sobrenome;

	@Column(nullable = false, unique = true, length = 254)
	private String email;
	
	@Column(length = 60)
	private String senha;
	
	@Column(nullable = false)
	private boolean google;
	
	@Column(nullable = false, length = 20)
	private String telefone;
	
	@Column(length = 36)
	private String foto;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@Column(nullable = false, length = 100)
	private String especializacao;
	
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "medico")
	private List<Dependente> dependentes;

	@Override
	public String getRole() {
		return "ROLE_MEDICO";
	}
}
