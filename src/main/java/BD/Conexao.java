package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection conexao = null;

    // Método para obter a conexão
    public static Connection conectar() {
        if (conexao == null) { // Apenas cria uma nova conexão se ainda não houver uma
            try {
                String url = "jdbc:postgresql://localhost:5432/JonesPetShop";
                String usuario = "postgres";
                String senha = "admin";

                Class.forName("org.postgresql.Driver");
                conexao = DriverManager.getConnection(url, usuario, senha);
                System.out.println("Conexão bem sucedida!");
            } catch (ClassNotFoundException ex) {
                System.out.println("Driver do banco de dados não localizado: " + ex.getMessage());
                ex.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao acessar o Banco: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return conexao;
    }

    // Método para fechar a conexão (opcional)
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
                System.out.println("Conexão fechada com sucesso.");
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao fechar a conexão: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}