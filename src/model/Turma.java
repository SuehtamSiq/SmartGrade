package model;

public class Turma {
    private int idTurma;
    private String codigo;
    private String periodoSemestre;

    public Turma() {}

    public Turma(int idTurma, String codigo, String periodoSemestre) {
        this.idTurma = idTurma;
        this.codigo = codigo;
        this.periodoSemestre = periodoSemestre;
    }

    public Turma(String codigo, String periodoSemestre) {
        this.codigo = codigo;
        this.periodoSemestre = periodoSemestre;
    }

    public int getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(int idTurma) {
        this.idTurma = idTurma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPeriodoSemestre() {
        return periodoSemestre;
    }

    public void setPeriodoSemestre(String periodoSemestre) {
        this.periodoSemestre = periodoSemestre;
    }
}