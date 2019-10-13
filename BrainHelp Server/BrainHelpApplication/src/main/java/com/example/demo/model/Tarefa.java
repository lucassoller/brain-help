package com.example.demo.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.example.demo.model.enumerated.StatusTarefa;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codTarefa;
	
	@Column(nullable = false)
	private String tarefa;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(nullable = false)
	private Date dataRealizacao;
	
	@Column(nullable = false)
	private StatusTarefa statusTarefa;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codDiagnosticado")
	private Diagnosticado diagnosticado;

	public Integer getCodTarefa() {
		return codTarefa;
	}

	public void setCodTarefa(Integer codTarefa) {
		this.codTarefa = codTarefa;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Diagnosticado getDiagnosticado() {
		return diagnosticado;
	}

	public void setDiagnosticado(Diagnosticado diagnosticado) {
		this.diagnosticado = diagnosticado;
	}

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public Tarefa(Integer codTarefa, String tarefa, String descricao, Date dataRealizacao, StatusTarefa statusTarefa,
			Diagnosticado diagnosticado) {
		super();
		this.codTarefa = codTarefa;
		this.tarefa = tarefa;
		this.descricao = descricao;
		this.dataRealizacao = dataRealizacao;
		this.statusTarefa = statusTarefa;
		this.diagnosticado = diagnosticado;
	}

	public Tarefa() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
