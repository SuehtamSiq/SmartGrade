package dao;

import conexao.ConexaoBD;
import model.RegistroAcademico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcademicoDAO {

    public AcademicoDAO() {
        criarTabelaSeNaoExistir();
    }

    private void criarTabelaSeNaoExistir() {
        // Tabela criada com tipos DECIMAL(5,2) para suportar notas como 10.00 ou 07.50
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

    public boolean salvarRegistro(RegistroAcademico registro) {
        String sql = "INSERT INTO registros_academicos (id_aluno, id_disciplina, id_turma, "
                   + "nota_p1, nota_p2, nota_trabalho, media_final, total_faltas, "
                   + "percentual_frequencia, situacao_academica) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Conexão agora é aberta e fechada localmente para evitar vazamento (Too many connections)
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, registro.getIdAluno());
            stmt.setInt(2, registro.getIdDisciplina());
            stmt.setInt(3, registro.getIdTurma());
            stmt.setBigDecimal(4, registro.getNotaP1());
            stmt.setBigDecimal(5, registro.getNotaP2());
            stmt.setBigDecimal(6, registro.getNotaTrabalho());
            stmt.setBigDecimal(7, registro.getMediaFinal());
            stmt.setInt(8, registro.getTotalFaltas());
            stmt.setBigDecimal(9, registro.getPercentualFrequencia());
            stmt.setString(10, registro.getSituacaoAcademica());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao salvar registro: " + e.getMessage());
            return false;
        }
    }

    public boolean atualizarNotasFaltas(RegistroAcademico registro) {
        String sql = "UPDATE registros_academicos SET nota_p1 = ?, nota_p2 = ?, nota_trabalho = ?, "
                   + "media_final = ?, total_faltas = ?, percentual_frequencia = ?, "
                   + "situacao_academica = ? WHERE id_registro = ?";
        
        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setBigDecimal(1, registro.getNotaP1());
            stmt.setBigDecimal(2, registro.getNotaP2());
            stmt.setBigDecimal(3, registro.getNotaTrabalho());
            stmt.setBigDecimal(4, registro.getMediaFinal());
            stmt.setInt(5, registro.getTotalFaltas());
            stmt.setBigDecimal(6, registro.getPercentualFrequencia());
            stmt.setString(7, registro.getSituacaoAcademica());
            stmt.setInt(8, registro.getIdRegistro());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar notas e faltas: " + e.getMessage());
            return false;
        }
    }

    public List<RegistroAcademico> buscarPorTurmaDisciplina(int idTurma, int idDisc) {
        String sql = "SELECT * FROM registros_academicos WHERE id_turma = ? AND id_disciplina = ?";
        List<RegistroAcademico> lista = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTurma);
            stmt.setInt(2, idDisc);

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
            System.out.println("Registros encontrados no banco: " + lista.size());

        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar registros: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
}