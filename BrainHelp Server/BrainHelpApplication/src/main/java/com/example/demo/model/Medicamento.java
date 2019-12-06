package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.demo.model.enumerated.Frequencia;
import com.example.demo.model.enumerated.Medida;

@Entity
public class Medicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codMedicamento;
	
	@Column(nullable = false)
	private String nomeMedicamento;
	
	private String funcao;
	
	private String contraIndicacoes;
	
	private String efeitosColaterais;
	
	@Column(nullable = false)
	private int quantidade;
	
	@Column(nullable = false)
	private Medida medida;
	
	private Date horario;
	
	private Frequencia frequencia;
	
	@Lob
	@Column(length = 2000)
	private String foto;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
	private Diagnosticado diagnosticado;

	public Integer getCodMedicamento() {
		return codMedicamento;
	}

	public void setCodMedicamento(Integer codMedicamento) {
		this.codMedicamento = codMedicamento;
	}

	public String getNomeMedicamento() {
		return nomeMedicamento;
	}

	public void setNomeMedicamento(String nomeMedicamento) {
		this.nomeMedicamento = nomeMedicamento;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getContraIndicacoes() {
		return contraIndicacoes;
	}

	public void setContraIndicacoes(String contraIndicacoes) {
		this.contraIndicacoes = contraIndicacoes;
	}

	public String getEfeitosColaterais() {
		return efeitosColaterais;
	}

	public void setEfeitosColaterais(String efeitosColaterais) {
		this.efeitosColaterais = efeitosColaterais;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Medida getMedida() {
		return medida;
	}

	public void setMedida(Medida medida) {
		this.medida = medida;
	}

	public Diagnosticado getDiagnosticado() {
		return diagnosticado;
	}

	public void setDiagnosticado(Diagnosticado diagnosticado) {
		this.diagnosticado = diagnosticado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public Medicamento(Integer codMedicamento, String nomeMedicamento, String funcao, String contraIndicacoes,
			String efeitosColaterais, int quantidade, Medida medida, Date horario, Frequencia frequencia, String foto,
			Diagnosticado diagnosticado) {
		super();
		this.codMedicamento = codMedicamento;
		this.nomeMedicamento = nomeMedicamento;
		this.funcao = funcao;
		this.contraIndicacoes = contraIndicacoes;
		this.efeitosColaterais = efeitosColaterais;
		this.quantidade = quantidade;
		this.medida = medida;
		this.horario = horario;
		this.frequencia = frequencia;
		this.foto = foto;
		this.diagnosticado = diagnosticado;
	}

	public Medicamento() {
		super();
		// TODO Auto-generated constructor stub
	}
}