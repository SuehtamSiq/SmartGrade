package dao;

import conexao.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaAlunoDAO {

    public void matricularAluno(int idTurma, int idAluno) {
        String sql = "INSERT INTO turma_alunos (id_turma, id_aluno) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            stmt.setInt(2, idAluno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao vincular aluno na turma: " + e.getMessage());
        }
    }

    public void desmatricularAluno(int idTurma, int idAluno) {
        String sql = "DELETE FROM turma_alunos WHERE id_turma = ? AND id_aluno = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            stmt.setInt(2, idAluno);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao desvincular aluno da turma: " + e.getMessage());
        }
    }

    // Retorna os IDs dos alunos matriculados em uma determinada turma
    public List<Integer> listarAlunosPorTurma(int idTurma) {
        String sql = "SELECT id_aluno FROM turma_alunos WHERE id_turma = ?";
        List<Integer> alunosIds = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    alunosIds.add(rs.getInt("id_aluno"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alunos da turma: " + e.getMessage());
        }
        return alunosIds;
    }
}