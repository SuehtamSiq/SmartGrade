package conexao;

import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        Connection conn = ConexaoBD.getConexao();
        if (conn != null) {
            System.out.println("Conexão com smartgrade_db realizada com sucesso!");
        } else {
            System.out.println("Falha ao conectar.");
        }
    }
}