
package Servico.DAO;

import Atendimento.DAO.AnimalDAO;
import Atendimento.DAO.ClienteDAO;
import Servico.Model.Agendamento;
import Atendimento.Model.Animal;
import Atendimento.Model.Cliente;
import BD.Conexao;
import Financeiro.DAO.ItemDAO;
import Financeiro.Model.Item;
import Financeiro.Model.Servico;
import Servico.Model.Profissional;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO {

    private Connection conexao;

    public AgendamentoDAO() {
        this.conexao = Conexao.conectar();
    }

    public void adicionarAgendamento(Agendamento agendamento) {
        String sql = "INSERT INTO Agendamento (animal_id, cliente_id, servico_id, hora, data, profissional_id, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, agendamento.getAnimal().getId());
            stmt.setInt(2, agendamento.getCliente().getId());
            stmt.setInt(3, agendamento.getServico().getId());
            stmt.setTime(4, Time.valueOf(agendamento.getHora()));
            stmt.setDate(5, Date.valueOf(agendamento.getData()));
            stmt.setInt(6, agendamento.getProfissional().getId());
            stmt.setString(7, agendamento.getStatus());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idGerado = generatedKeys.getInt(1);
                agendamento.setId(idGerado);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar agendamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void atualizarStatusAgendamento(int agendamentoId, String novoStatus) {
        String sql = "UPDATE Agendamento SET status = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, agendamentoId);
            stmt.executeUpdate();
            System.out.println("Status do agendamento atualizado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar status do agendamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void atualizarAgendamento(Agendamento agendamento) {
        String sql = "UPDATE Agendamento SET animal_id = ?, cliente_id = ?, servico_id = ?, hora = ?, data = ?, profissional_id = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, agendamento.getAnimal().getId());
            stmt.setInt(2, agendamento.getCliente().getId());
            stmt.setInt(3, agendamento.getServico().getId());
            stmt.setTime(4, Time.valueOf(agendamento.getHora()));
            stmt.setDate(5, Date.valueOf(agendamento.getData()));
            stmt.setInt(6, agendamento.getProfissional().getId());
            stmt.setString(7, agendamento.getStatus());
            stmt.setInt(8, agendamento.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar agendamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void removerAgendamento(int id) {
        String sql = "DELETE FROM Agendamento WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao remover agendamento: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public Agendamento buscarAgendamento(int id) {
        String sql = "SELECT * FROM Agendamento WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAgendamento(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar agendamento: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public List<Agendamento> listarTodosAgendamentos() {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento ORDER BY id";
        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                agendamentos.add(mapResultSetToAgendamento(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar agendamentos: " + ex.getMessage());
            ex.printStackTrace();
        }
        return agendamentos;
    }
    
    public List<Agendamento> buscarAgendamentosPorAnimal(int animalId) {
        List<Agendamento> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM Agendamento WHERE animal_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, animalId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Agendamento agendamento = mapResultSetToAgendamento(rs);
                if(!agendamento.getStatus().equals("Cancelado")){
                    agendamentos.add(agendamento);
                }       
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar agendamentos por animal: " + ex.getMessage());
            ex.printStackTrace();
        }
        return agendamentos;
    }

    private Agendamento mapResultSetToAgendamento(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int animalId = rs.getInt("animal_id");
        int clienteId = rs.getInt("cliente_id");
        int servicoId = rs.getInt("servico_id");
        LocalTime hora = rs.getTime("hora").toLocalTime();
        LocalDate data = rs.getDate("data").toLocalDate();
        int profissionalId = rs.getInt("profissional_id");
        String status = rs.getString("status");

        // Recuperando os objetos relacionados usando DAOs
        Animal animal = new AnimalDAO().buscarAnimalPorId(animalId);
        Cliente cliente = new ClienteDAO().buscarClientePorId(clienteId);

        // Usando ItemDAO para buscar o serviço (considerando que ItemDAO retorna ambos, Produto e Servico)
        Servico servico = null;
        Item item = new ItemDAO().getItemById(servicoId);
        if (item instanceof Servico) {
            servico = (Servico) item;
        } else {
            throw new SQLException("O item com ID " + servicoId + " não é um serviço.");
        }

        Profissional profissional = new ProfissionalDAO().buscarProfissionalPorId(profissionalId);

        return new Agendamento(id, animal, cliente, servico, hora, data, profissional, status);
    }

}

