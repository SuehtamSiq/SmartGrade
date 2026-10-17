package dao;

import conexao.ConexaoBD;
import model.Disciplina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO {

    public DisciplinaDAO() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS disciplinas ("
                   + "id_disciplina INT AUTO_INCREMENT PRIMARY KEY, "
                   + "nome VARCHAR(150) NOT NULL, "
                   + "codigo VARCHAR(50) NOT NULL UNIQUE"
                   + ")";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar/criar a tabela disciplinas: " + e.getMessage());
        }
    }

    public void cadastrar(Disciplina disc) {
        String sql = "INSERT INTO disciplinas (nome, codigo) VALUES (?, ?)";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, disc.getNome());
            stmt.setString(2, disc.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar disciplina: " + e.getMessage());
        }
    }

    public List<Disciplina> listarTodos() {
        String sql = "SELECT * FROM disciplinas";
        List<Disciplina> lista = new ArrayList<>();
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina d = new Disciplina();
                d.setIdDisciplina(rs.getInt("id_disciplina")); 
                d.setNome(rs.getString("nome"));
                d.setCodigo(rs.getString("codigo"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar disciplinas: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Disciplina disc) {
        String sql = "UPDATE disciplinas SET nome = ?, codigo = ? WHERE id_disciplina = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, disc.getNome());
            stmt.setString(2, disc.getCodigo());
            stmt.setInt(3, disc.getIdDisciplina());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disciplina: " + e.getMessage());
        }
    }

    public void excluir(int idDisciplina) {
        String sql = "DELETE FROM disciplinas WHERE id_disciplina = ?";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idDisciplina);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir disciplina: " + e.getMessage());
        }
    }
}