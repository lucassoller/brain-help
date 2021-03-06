package com.example.app.model;

import com.example.app.model.enumerated.AvaliacaoDesempenho;
import com.example.app.model.enumerated.EstagioAlzheimer;
import com.example.app.model.enumerated.Sexo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Diagnosticado implements Serializable {
  
    private Integer codDiagnosticado;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String senha;
    private int idade;
    private Sexo sexo;
    private EstagioAlzheimer estagioAlzheimer;
    private String foto;
    private Endereco endereco;
    private String chaveSeguranca;
    private Date dataDiagnostico;
    private Medico medico;
    private List<Vinculo> vinculos;
    private List<Medicamento> medicamentos;
    private List<Fotografia> fotografias;
    private List<Musica> musicas;
    private List<Tarefa> tarefas;
    private List<Desempenho> desempenhos;
    private AvaliacaoDesempenho desempenhoAtual;
    private boolean google;

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
