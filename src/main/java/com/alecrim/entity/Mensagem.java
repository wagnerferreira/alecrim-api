package com.alecrim.entity;

public class Mensagem {
    
    private Pessoa remetente;
    private Pessoa destinatario;
    private String mesnagem;

    public Pessoa getDestinatario() {
        return this.destinatario;
    }

    public void setDestinatario(Pessoa destinatario) {
        this.destinatario = destinatario;
    }

    public String getMesnagem() {
        return this.mesnagem;
    }

    public void setMesnagem(String mesnagem) {
        this.mesnagem = mesnagem;
    }

    public Pessoa getRemente() {
        return this.remetente;
    }

    public void setRemetente(Pessoa remetente) {
        this.remetente = remetente;
    }

}