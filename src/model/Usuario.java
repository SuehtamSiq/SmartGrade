package model;

public class Usuario {
    private int idUsuario;
    private String login;
    private String senha;
    private String perfil;

    // Crie os Getters e Setters para todas as variáveis
    public String getPerfil() { return perfil; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
}