package dao;

import conexao.ConexaoBD;
import model.Avaliacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public void cadastrar(Avaliacao av) {
        String sql = "INSERT INTO avaliacoes (id_turma, titulo, peso, data_entrega) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, av.getIdTurma());
            stmt.setString(2, av.getTitulo());
            stmt.setDouble(3, av.getPeso());
            stmt.setDate(4, av.getDataEntrega() != null ? new java.sql.Date(av.getDataEntrega().getTime()) : null);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar avaliação: " + e.getMessage());
        }
    }

    public List<Avaliacao> listarPorTurma(int idTurma) {
        String sql = "SELECT * FROM avaliacoes WHERE id_turma = ?";
        List<Avaliacao> lista = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTurma);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Avaliacao a = new Avaliacao();
                    a.setIdAvaliacao(rs.getInt("id")); // confira se no banco é 'id' ou 'id_avaliacao'
                    a.setIdTurma(rs.getInt("id_turma"));
                    a.setTitulo(rs.getString("titulo"));
                    a.setPeso(rs.getDouble("peso"));
                    a.setDataEntrega(rs.getDate("data_entrega"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar avaliações da turma: " + e.getMessage());
        }
        return lista;
    }

    public void excluir(int idAvaliacao) {
        String sql = "DELETE FROM avaliacoes WHERE id = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAvaliacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir avaliação: " + e.getMessage());
        }
    }
}