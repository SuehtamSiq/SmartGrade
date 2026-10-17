package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    // 1. URL apenas do servidor (sem o nome do banco no final)
    private static final String URL_SERVIDOR = "jdbc:mysql://localhost:3306/";
    // 2. URL completa com o banco
    private static final String URL_BANCO = "jdbc:mysql://localhost:3306/smartgrade_db";
    private static final String USUARIO = "root";
    private static final String SENHA = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : ""; 

    public static Connection getConexao() {
        try {
            // Passo 1: Conecta no servidor MySQL "solto"
            Connection connServidor = DriverManager.getConnection(URL_SERVIDOR, USUARIO, SENHA);
            Statement stmt = connServidor.createStatement();
            
            // Passo 2: Manda o MySQL criar o banco apenas se ele não existir
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS smartgrade_db");
            
            // Fecha essa conexão temporária
            stmt.close();
            connServidor.close();

            // Passo 3: Retorna a conexão definitiva já conectada no banco correto
            return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
            
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }
}