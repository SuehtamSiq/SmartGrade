package model;

import java.util.Date;

public class Avaliacao {
    private int idAvaliacao;
    private int idTurma;
    private String titulo;
    private double peso;
    private Date dataEntrega;

    public Avaliacao() {}

    public Avaliacao(int idAvaliacao, int idTurma, String titulo, double peso, Date dataEntrega) {
        this.idAvaliacao = idAvaliacao;
        this.idTurma = idTurma;
        this.titulo = titulo;
        this.peso = peso;
        this.dataEntrega = dataEntrega;
    }

    public Avaliacao(int idTurma, String titulo, double peso, Date dataEntrega) {
        this.idTurma = idTurma;
        this.titulo = titulo;
        this.peso = peso;
        this.dataEntrega = dataEntrega;
    }

    // Getters e Setters
    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
}