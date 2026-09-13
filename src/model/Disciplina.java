package model;

public class Disciplina {
    private int idDisciplina;
    private String codigo;
    private String nome;
    private int cargaHoraria;
    private int totalAulas;

    public Disciplina() {}

    public Disciplina(int idDisciplina, String codigo, String nome, int cargaHoraria, int totalAulas) {
        this.idDisciplina = idDisciplina;
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.totalAulas = totalAulas;
    }

    public Disciplina(String codigo, String nome, int cargaHoraria, int totalAulas) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.totalAulas = totalAulas;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(int totalAulas) {
        this.totalAulas = totalAulas;
    }
}
