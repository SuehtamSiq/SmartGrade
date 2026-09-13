package model;

public class Aluno {
    private int idAluno;
    private String ra;
    private String nome;
    private String email;

    public Aluno() {}

    public Aluno(int idAluno, String ra, String nome, String email) {
        this.idAluno = idAluno;
        this.ra = ra;
        this.nome = nome;
        this.email = email;
    }

    public Aluno(String ra, String nome, String email) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}