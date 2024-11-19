package Main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import BD.Conexao;


public class GerenciaLogin {
    private static final String ARQUIVO_BINARIO = "usuarios.dat";
    private static final int DESLOCAMENTO = 4; // Valor de deslocamento para criptografia

    public void verificaArquivo() {
        if (!arquivoExiste()) {
            System.out.println("Arquivo criado com 3 usuários padrão.");
        }

        // Simulação de login
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome de usuário: ");
        String username = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String password = scanner.nextLine();

        if (verificarLogin(username, password)) {
            System.out.println("Login bem-sucedido!");
        } else {
            System.out.println("Nome de usuário ou senha incorretos.");
        }
    }

    // Função para criptografar a senha com deslocamento
    public static String criptografarSenha(String senha) {
        StringBuilder criptografada = new StringBuilder();

        for (char c : senha.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                char deslocado = (char) ((c - base + DESLOCAMENTO) % 26 + base);
                criptografada.append(deslocado);
            } else if (Character.isDigit(c)) {
                char deslocado = (char) ((c - '0' + DESLOCAMENTO) % 10 + '0');
                criptografada.append(deslocado);
            } else {
                criptografada.append(c); // Mantém caracteres não alfabéticos inalterados
            }
        }

        return criptografada.toString();
    }

    // Função para salvar senhas criptografadas
    public static void salvarSenhas(Map<String, String> usuarios) {
        Connection conn = Conexao.conectar();
        String sql = "INSERT INTO usuarios (username, senha) VALUES (?, ?) ON CONFLICT (username) DO UPDATE SET senha = EXCLUDED.senha";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Map.Entry<String, String> entry : usuarios.entrySet()) {
                ps.setString(1, entry.getKey());
                ps.setString(2, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Conexão não fechada aqui, será gerenciada em outra parte
    }

    // Função para verificar o login
    public static boolean verificarLogin(String username, String password) {
        Connection conn = Conexao.conectar();
        String sql = "SELECT senha FROM usuarios WHERE username = ?";
        String senhaCriptografada = criptografarSenha(password);
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String senhaArmazenada = rs.getString("senha");
                    return senhaCriptografada.equals(senhaArmazenada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Conexão não fechada aqui, será gerenciada em outra parte
        return false;
    }
    // Função para verificar se o arquivo existe e, se não, criar com 3 usuários padrão
    public static boolean arquivoExiste() {
        File file = new File(ARQUIVO_BINARIO);
        if (!file.exists()) {
            // Cria o arquivo e adiciona 3 usuários padrão
            Map<String, String> usuariosPadrao = new HashMap<>();
            usuariosPadrao.put("marcus", "123");
            usuariosPadrao.put("lucas", "456");
            usuariosPadrao.put("augusto", "789");

            // Criptografa as senhas antes de salvar
            Map<String, String> usuariosPadraoCriptografados = new HashMap<>();
            for (Map.Entry<String, String> entry : usuariosPadrao.entrySet()) {
                String usuario = entry.getKey();
                String senhaCriptografada = criptografarSenha(entry.getValue());
                usuariosPadraoCriptografados.put(usuario, senhaCriptografada);
            }
            salvarSenhas(usuariosPadraoCriptografados);
            return false;
        }
        return true;
    }

    // Função para verificar se um usuário já está cadastrado
    public static boolean verificarUsuarioExistente(String username) {
        Connection conn = Conexao.conectar();
        String sql = "SELECT 1 FROM usuarios WHERE username = ?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Conexão não fechada aqui, será gerenciada em outra parte
        return false;
    }

    // Função para adicionar um novo usuário
    public static boolean adicionarNovoUsuario(String novoUsuario, String novaSenha) {
        if (verificarUsuarioExistente(novoUsuario)) {
            System.out.println("Usuário já cadastrado.");
            return true;
        }

        Map<String, String> usuarios = new HashMap<>();
        usuarios.put(novoUsuario, criptografarSenha(novaSenha));

        salvarSenhas(usuarios);

        System.out.println("Usuário cadastrado com sucesso!");
        return false;
    }
}