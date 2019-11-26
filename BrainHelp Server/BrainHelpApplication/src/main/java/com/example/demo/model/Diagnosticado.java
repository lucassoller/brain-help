package com.example.demo.model;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import com.example.demo.model.enumerated.AvaliacaoDesempenho;
import com.example.demo.model.enumerated.EstagioAlzheimer;
import com.example.demo.model.enumerated.Sexo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Diagnosticado extends Usuario{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codDiagnosticado;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String sobrenome;
	
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(nullable = false)
	private String telefone;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private int idade;
	
	@Column(nullable = false)
	private Sexo sexo;
	
	@Column(nullable = false)
	private EstagioAlzheimer estagioAlzheimer;
	
	private String foto;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="codEndereco")
	private Endereco endereco;
	
	private String chaveSeguranca;
	
	private Date dataDiagnostico;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "codMedico")
	private Medico medico;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "diagnosticado")
	private List<Vinculo> vinculos;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "diagnosticado")
	private List<Medicamento> medicamentos;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "diagnosticado")
	private List<Fotografia> fotografias;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "diagnosticado")
	private List<Musica> musicas;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "diagnosticado")
	private List<Tarefa> tarefas;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "diagnosticado")
	private List<Desempenho> desempenhos;
	
	private AvaliacaoDesempenho desempenhoAtual;
	
	@Column(nullable = false)
	private boolean google;
	
	@JsonIgnore
    public Optional<String> getRole() {
        return Optional.of("Usuario normal");
    }

	public Integer getCodDiagnosticado() {
		return codDiagnosticado;
	}

	public void setCodDiagnosticado(Integer codDiagnosticado) {
		this.codDiagnosticado = codDiagnosticado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public EstagioAlzheimer getEstagioAlzheimer() {
		return estagioAlzheimer;
	}

	public void setEstagioAlzheimer(EstagioAlzheimer estagioAlzheimer) {
		this.estagioAlzheimer = estagioAlzheimer;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getChaveSeguranca() {
		return chaveSeguranca;
	}

	public void setChaveSeguranca(String chaveSeguranca) {
		this.chaveSeguranca = chaveSeguranca;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public List<Vinculo> getVinculos() {
		return vinculos;
	}

	public void setVinculos(List<Vinculo> vinculos) {
		this.vinculos = vinculos;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<Fotografia> getFotografias() {
		return fotografias;
	}

	public void setFotografias(List<Fotografia> fotografias) {
		this.fotografias = fotografias;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public void setMusicas(List<Musica> musicas) {
		this.musicas = musicas;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public List<Desempenho> getDesempenhos() {
		return desempenhos;
	}

	public void setDesempenhos(List<Desempenho> desempenhos) {
		this.desempenhos = desempenhos;
	}

	public AvaliacaoDesempenho getDesempenhoAtual() {
		return desempenhoAtual;
	}

	public void setDesempenhoAtual(AvaliacaoDesempenho desempenhoAtual) {
		this.desempenhoAtual = desempenhoAtual;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Date getDataDiagnostico() {
		return dataDiagnostico;
	}

	public void setDataDiagnostico(Date dataDiagnostico) {
		this.dataDiagnostico = dataDiagnostico;
	}

	public boolean isGoogle() {
		return google;
	}

	public void setGoogle(boolean google) {
		this.google = google;
	}

	public Diagnosticado(String email, String senha, Integer codDiagnosticado, String nome, String sobrenome,
			int idade, Sexo sexo, EstagioAlzheimer estagioAlzheimer, String telefone,
			String foto, Endereco endereco, String chaveSeguranca, Date dataDiagnostico, Medico medico,
			List<Vinculo> vinculos, List<Medicamento> medicamentos, List<Fotografia> fotografias, List<Musica> musicas,
			List<Tarefa> tarefas, List<Desempenho> desempenhos, AvaliacaoDesempenho desempenhoAtual, boolean google) {

		this.codDiagnosticado = codDiagnosticado;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.idade = idade;
		this.sexo = sexo;
		this.estagioAlzheimer = estagioAlzheimer;
		this.foto = foto;
		this.endereco = endereco;
		this.chaveSeguranca = chaveSeguranca;
		this.dataDiagnostico = dataDiagnostico;
		this.medico = medico;
		this.vinculos = vinculos;
		this.medicamentos = medicamentos;
		this.fotografias = fotografias;
		this.musicas = musicas;
		this.tarefas = tarefas;
		this.desempenhos = desempenhos;
		this.desempenhoAtual = desempenhoAtual;
		this.google = google;
	}

	public Diagnosticado() {
		super();
		// TODO Auto-generated constructor stub
	}
}
