package Atendimento.DAO;

import Atendimento.Model.Animal;
import Atendimento.Model.Cliente;
import BD.Conexao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
    private Connection conexao;

    public AnimalDAO() {
        this.conexao = Conexao.conectar();
    }

    // Create - Inserir um animal
    public void adicionarAnimal(Animal animal) {
        String sql = "INSERT INTO Animal (nome, data_nascimento, raca, peso, altura, dono_id, imagem) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, animal.getNome());
            stmt.setDate(2, Date.valueOf(animal.getDataNascimento()));
            stmt.setString(3, animal.getRaca());
            stmt.setFloat(4, animal.getPeso());
            stmt.setFloat(5, animal.getAltura());
            stmt.setInt(6, animal.getDono().getId());
            stmt.setBytes(7, animal.getImagem());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                animal.setId(generatedKeys.getInt(1)); // Atualiza o ID gerado no objeto animal
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar animal: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Read - Buscar todos os animais
    public List<Animal> listarTodosAnimais() {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM Animal WHERE ativo = TRUE";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Animal animal = mapResultSetToAnimal(rs);
                animais.add(animal);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar animais: " + ex.getMessage());
            ex.printStackTrace();
        }
        return animais;
    }

    // Read - Buscar um animal pelo ID
    public Animal buscarAnimalPorId(int id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAnimal(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar animal: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    // Update - Atualizar um animal
    public void atualizarAnimal(Animal animal) {
        String sql = "UPDATE Animal SET nome = ?, data_nascimento = ?, raca = ?, peso = ?, altura = ?, dono_id = ?, imagem = ?, ativo = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setDate(2, Date.valueOf(animal.getDataNascimento()));
            stmt.setString(3, animal.getRaca());
            stmt.setFloat(4, animal.getPeso());
            stmt.setFloat(5, animal.getAltura());
            stmt.setInt(6, animal.getDono().getId());
            stmt.setBytes(7, animal.getImagem());
            stmt.setBoolean(8, animal.getAtivo());
            stmt.setInt(9, animal.getId());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar animal: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Delete - Remover um animal
    public void removerAnimal(int id) {
        String sql = "DELETE FROM Animal WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao remover animal: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Método auxiliar para mapear o ResultSet para um objeto Animal
    private Animal mapResultSetToAnimal(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        LocalDate dataNascimento = rs.getDate("data_nascimento").toLocalDate();
        String raca = rs.getString("raca");
        float peso = rs.getFloat("peso");
        float altura = rs.getFloat("altura");
        int donoId = rs.getInt("dono_id");
        byte[] imagem = rs.getBytes("imagem");

        Cliente dono = new ClienteDAO().buscarClientePorId(donoId); // Recupera o dono usando ClienteDAO

        return new Animal(id, nome, dataNascimento, raca, peso, altura, dono, imagem);
    }
    
    public List<Animal> buscarAnimaisPorClienteId(int clienteId) {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM Animal WHERE cliente_id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Animal animal = mapResultSetToAnimal(rs);
                animais.add(animal);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar animais por cliente: " + ex.getMessage());
            ex.printStackTrace();
        }

        return animais;
    }
}

