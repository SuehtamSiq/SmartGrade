package dao;

import conexao.ConexaoBD;
import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void cadastrar(Aluno aluno) {
        String sql = "INSERT INTO alunos (nome, ra, email) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getRa());
            stmt.setString(3, aluno.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }

    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM alunos";
        List<Aluno> lista = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setIdAluno(rs.getInt("id_aluno")); 
                a.setNome(rs.getString("nome"));
                a.setRa(rs.getString("ra"));
                a.setEmail(rs.getString("email"));
                lista.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE alunos SET nome = ?, ra = ?, email = ? WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getRa());
            stmt.setString(3, aluno.getEmail());
            stmt.setInt(4, aluno.getIdAluno());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void excluir(int idAluno) {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir aluno: " + e.getMessage());
        }
    }
}