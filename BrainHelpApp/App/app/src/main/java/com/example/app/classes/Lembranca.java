package com.example.app.classes;

import java.util.Date;

public class Lembranca {

        private Integer codLembranca;

        private String titulo;

        private Date data;

        private String lembranca;

        private Vinculo vinculo;

        private Fotografia fotografia;

        private Musica musica;


        public Integer getCodLembranca() {
            return codLembranca;
        }

        public void setCodLembranca(Integer codLembranca) {
            this.codLembranca = codLembranca;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Date getData() {
            return data;
        }

        public void setData(Date data) {
            this.data = data;
        }

        public String getLembranca() {
            return lembranca;
        }

        public void setLembranca(String lembranca) {
            this.lembranca = lembranca;
        }

        public Vinculo getVinculo() {
            return vinculo;
        }

        public void setVinculo(Vinculo vinculo) {
            this.vinculo = vinculo;
        }

        public Fotografia getFotografia() {
            return fotografia;
        }

        public void setFotografia(Fotografia fotografia) {
            this.fotografia = fotografia;
        }

        public Musica getMusica() {
            return musica;
        }

        public void setMusica(Musica musica) {
            this.musica = musica;
        }

        public Lembranca(Integer codLembranca, String titulo, Date data, String lembranca, Vinculo vinculo,
                         Fotografia fotografia, Musica musica) {
            super();
            this.codLembranca = codLembranca;
            this.titulo = titulo;
            this.data = data;
            this.lembranca = lembranca;
            this.vinculo = vinculo;
            this.fotografia = fotografia;
            this.musica = musica;
        }

        public Lembranca() {
            super();
            // TODO Auto-generated constructor stub
        }

    }
