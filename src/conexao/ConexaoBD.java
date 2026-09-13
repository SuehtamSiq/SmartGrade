package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/smartgrade_db";
    private static final String USUARIO = "root";
    private static final String SENHA = ""; 

    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco: " + e.getMessage());
            return null;
        }
    }
}