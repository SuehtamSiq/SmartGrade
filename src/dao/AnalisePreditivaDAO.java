package dao;

import conexao.ConexaoBD;
import model.AnalisePreditiva;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalisePreditivaDAO {

    public void cadastrar(AnalisePreditiva analise) {
        String sql = "INSERT INTO analises_preditivas (id_registro, score_risco, nivel_risco, data_analise) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, analise.getIdRegistro());
            stmt.setBigDecimal(2, analise.getScoreRisco());
            stmt.setString(3, analise.getNivelRisco());
            stmt.setTimestamp(4, analise.getDataAnalise() != null ? analise.getDataAnalise() : new Timestamp(System.currentTimeMillis()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar análise preditiva: " + e.getMessage());
        }
    }

    public List<AnalisePreditiva> listarPorRegistro(int idRegistro) {
        String sql = "SELECT * FROM analises_preditivas WHERE id_registro = ?";
        List<AnalisePreditiva> lista = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRegistro);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    AnalisePreditiva a = new AnalisePreditiva();
                    a.setIdAnalise(rs.getInt("id_analise")); // Coluna exata do seu phpMyAdmin
                    a.setIdRegistro(rs.getInt("id_registro"));
                    a.setScoreRisco(rs.getBigDecimal("score_risco"));
                    a.setNivelRisco(rs.getString("nivel_risco"));
                    a.setDataAnalise(rs.getTimestamp("data_analise"));

                    lista.add(a);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar análises preditivas: " + e.getMessage());
        }
        return lista;
    }
}