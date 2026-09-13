package dao;

import conexao.ConexaoBD;
import model.Turma;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public void cadastrar(Turma turma) {
        String sql = "INSERT INTO turmas (codigo, periodo_semestre) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getCodigo());
            stmt.setString(2, turma.getPeriodoSemestre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar turma: " + e.getMessage());
        }
    }

    public List<Turma> listarTodos() {
        String sql = "SELECT * FROM turmas";
        List<Turma> lista = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma t = new Turma();
                t.setIdTurma(rs.getInt("id_turma")); 
                t.setCodigo(rs.getString("codigo"));
                t.setPeriodoSemestre(rs.getString("periodo_semestre")); 
                lista.add(t);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar turmas: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Turma turma) {
        String sql = "UPDATE turmas SET codigo = ?, periodo_semestre = ? WHERE id_turma = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, turma.getCodigo());
            stmt.setString(2, turma.getPeriodoSemestre());
            stmt.setInt(3, turma.getIdTurma());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar turma: " + e.getMessage());
        }
    }

    public void excluir(int idTurma) {
        String sql = "DELETE FROM turmas WHERE id_turma = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir turma: " + e.getMessage());
        }
    }
}