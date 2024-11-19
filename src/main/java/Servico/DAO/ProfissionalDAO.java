package Servico.DAO;


import BD.Conexao;
import Servico.Model.Profissional;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class ProfissionalDAO {

    private Connection conexao;

    public ProfissionalDAO() {
        this.conexao = Conexao.conectar();
    }

    public void adicionarProfissional(Profissional profissional) {
        String sql = "INSERT INTO Profissional (nome, especialidade) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getEspecialidade());
            stmt.executeUpdate();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);
                profissional.setId(idGerado);  
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar profissional: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean removerProfissional(int id) {
        String sql = "DELETE FROM Profissional WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao remover profissional: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public boolean atualizarProfissional(Profissional profissionalAtualizado) {
        String sql = "UPDATE Profissional SET nome = ?, especialidade = ?, ativo = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, profissionalAtualizado.getNome());
            stmt.setString(2, profissionalAtualizado.getEspecialidade());
            stmt.setBoolean(3, profissionalAtualizado.getAtivo());
            stmt.setDouble(4, profissionalAtualizado.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar profissional: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    public Profissional buscarProfissionalPorId(int id) {
        String sql = "SELECT * FROM Profissional WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");
                return new Profissional(id, nome, especialidade);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar profissional: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public List<Profissional> listarTodosProfissionais() {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT * FROM Profissional WHERE ativo = TRUE";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");
                profissionais.add(new Profissional(id, nome, especialidade));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar profissionais: " + ex.getMessage());
            ex.printStackTrace();
        }
        return profissionais;
    }
}


