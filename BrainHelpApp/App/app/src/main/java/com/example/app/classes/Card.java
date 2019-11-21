package com.example.app.classes;

public class Card {
    private String id;
    private int idFoto;
    private boolean virada;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public Card(String id, int idFoto) {
        this.id = id;
        this.idFoto = idFoto;
    }

    public boolean isVirada() {
        return virada;
    }

    public void setVirada(boolean virada) {
        this.virada = virada;
    }

    public Card() {
    }
}
