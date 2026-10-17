package dao;

import conexao.ConexaoBD;
import model.Avaliacao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public AvaliacaoDAO() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS avaliacoes ("
                   + "id_avaliacao INT AUTO_INCREMENT PRIMARY KEY, "
                   + "id_turma INT NOT NULL, "
                   + "titulo VARCHAR(200) NOT NULL, "
                   + "peso DOUBLE, "
                   + "data_entrega DATE"
                   + ")";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar/criar a tabela avaliacoes: " + e.getMessage());
        }
    }

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
                    // Padronizado para 'id_avaliacao'
                    a.setIdAvaliacao(rs.getInt("id_avaliacao")); 
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
        // Padronizado de 'WHERE id = ?' para 'WHERE id_avaliacao = ?'
        String sql = "DELETE FROM avaliacoes WHERE id_avaliacao = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAvaliacao);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir avaliação: " + e.getMessage());
        }
    }
}