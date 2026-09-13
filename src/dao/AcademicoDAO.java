package dao;

import conexao.ConexaoBD;
import model.RegistroAcademico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcademicoDAO {

    private Connection conexao;

    public AcademicoDAO() {
        this.conexao = ConexaoBD.getConexao();
    }

    public boolean salvarRegistro(RegistroAcademico registro) {
        String sql = "INSERT INTO registros_academicos (id_aluno, id_disciplina, id_turma, "
                   + "nota_p1, nota_p2, nota_trabalho, media_final, total_faltas, "
                   + "percentual_frequencia, situacao_academica) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
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

        // Abre uma conexão nova e limpa direto para esta busca
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
