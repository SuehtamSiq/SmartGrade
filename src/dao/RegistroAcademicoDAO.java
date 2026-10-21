package dao;

import conexao.ConexaoBD;
import model.RegistroAcademico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroAcademicoDAO {

    public RegistroAcademicoDAO() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS registros_academicos ("
                   + "id_registro INT AUTO_INCREMENT PRIMARY KEY, "
                   + "id_aluno INT NOT NULL, "
                   + "id_disciplina INT NOT NULL, "
                   + "id_turma INT NOT NULL, "
                   + "nota_p1 DECIMAL(5,2), "
                   + "nota_p2 DECIMAL(5,2), "
                   + "nota_trabalho DECIMAL(5,2), "
                   + "media_final DECIMAL(5,2), "
                   + "total_faltas INT, "
                   + "percentual_frequencia DECIMAL(5,2), "
                   + "situacao_academica VARCHAR(50)"
                   + ")";
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar/criar a tabela registros_academicos: " + e.getMessage());
        }
    }

    public void cadastrar(RegistroAcademico reg) {
        String sql = "INSERT INTO registros_academicos (id_aluno, id_disciplina, id_turma, "
                   + "nota_p1, nota_p2, nota_trabalho, media_final, total_faltas, "
                   + "percentual_frequencia, situacao_academica) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reg.getIdAluno());
            stmt.setInt(2, reg.getIdDisciplina());
            stmt.setInt(3, reg.getIdTurma());
            stmt.setBigDecimal(4, reg.getNotaP1());
            stmt.setBigDecimal(5, reg.getNotaP2());
            stmt.setBigDecimal(6, reg.getNotaTrabalho());
            stmt.setBigDecimal(7, reg.getMediaFinal());
            stmt.setInt(8, reg.getTotalFaltas());
            stmt.setBigDecimal(9, reg.getPercentualFrequencia());
            stmt.setString(10, reg.getSituacaoAcademica());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar registro acadêmico: " + e.getMessage());
        }
    }

    public List<RegistroAcademico> listarPorAluno(int idAluno) {
        String sql = "SELECT * FROM registros_academicos WHERE id_aluno = ?";
        List<RegistroAcademico> lista = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    RegistroAcademico reg = new RegistroAcademico();
                    reg.setIdRegistro(rs.getInt("id_registro"));
                    reg.setIdAluno(rs.getInt("id_aluno"));
                    reg.setIdDisciplina(rs.getInt("id_disciplina"));
                    reg.setIdTurma(rs.getInt("id_turma"));
                    reg.setNotaP1(rs.getBigDecimal("nota_p1"));
                    reg.setNotaP2(rs.getBigDecimal("nota_p2"));
                    reg.setNotaTrabalho(rs.getBigDecimal("nota_trabalho"));
                    reg.setMediaFinal(rs.getBigDecimal("media_final"));
                    reg.setTotalFaltas(rs.getInt("total_faltas"));
                    reg.setPercentualFrequencia(rs.getBigDecimal("percentual_frequencia"));
                    reg.setSituacaoAcademica(rs.getString("situacao_academica"));

                    lista.add(reg);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar registros acadêmicos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(RegistroAcademico reg) {
        String sql = "UPDATE registros_academicos SET id_aluno = ?, id_disciplina = ?, id_turma = ?, "
                   + "nota_p1 = ?, nota_p2 = ?, nota_trabalho = ?, media_final = ?, total_faltas = ?, "
                   + "percentual_frequencia = ?, situacao_academica = ? WHERE id_registro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reg.getIdAluno());
            stmt.setInt(2, reg.getIdDisciplina());
            stmt.setInt(3, reg.getIdTurma());
            stmt.setBigDecimal(4, reg.getNotaP1());
            stmt.setBigDecimal(5, reg.getNotaP2());
            stmt.setBigDecimal(6, reg.getNotaTrabalho());
            stmt.setBigDecimal(7, reg.getMediaFinal());
            stmt.setInt(8, reg.getTotalFaltas());
            stmt.setBigDecimal(9, reg.getPercentualFrequencia());
            stmt.setString(10, reg.getSituacaoAcademica());
            stmt.setInt(11, reg.getIdRegistro());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar registro acadêmico: " + e.getMessage());
        }
    }

    public void excluir(int idRegistro) {
        String sql = "DELETE FROM registros_academicos WHERE id_registro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRegistro);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir registro acadêmico: " + e.getMessage());
        }
    }
    
    public void atualizarNotaFalta(int idAluno, double notaP1, double notaP2, double notaTrabalho, int novasFaltas) {
        
        // 1. O Java calcula a nova Média automaticamente
        double novaMedia = (notaP1 + notaP2 + notaTrabalho) / 3.0;
        
        // 2. O Java calcula a nova Frequência (100% menos 2.5% por cada falta)
        double novaFrequencia = 100.0 - (novasFaltas * 2.5);
        if (novaFrequencia < 0) novaFrequencia = 0.0; // Garante que não fica negativo
        
        // 3. A instrução SQL agora atualiza TUDO: notas, faltas, média e percentual!
        String sql = "UPDATE registros_academicos SET nota_p1 = ?, nota_p2 = ?, nota_trabalho = ?, total_faltas = ?, media_final = ?, percentual_frequencia = ? WHERE id_aluno = ?";
        
        try (java.sql.Connection conn = conexao.ConexaoBD.getConexao();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setDouble(1, notaP1);
            stmt.setDouble(2, notaP2);
            stmt.setDouble(3, notaTrabalho);
            stmt.setInt(4, novasFaltas);
            stmt.setDouble(5, novaMedia);       // Envia a média calculada
            stmt.setDouble(6, novaFrequencia);  // Envia a frequência calculada
            stmt.setInt(7, idAluno);
            
            stmt.executeUpdate();
            
        } catch (java.sql.SQLException e) {
            throw new RuntimeException("Erro ao atualizar notas: " + e.getMessage());
        }
    }
}