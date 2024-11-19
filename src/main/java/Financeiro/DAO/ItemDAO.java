package Financeiro.DAO;

import Financeiro.Model.Item;
import Financeiro.Model.Produto;
import Financeiro.Model.Servico;
import Servico.Model.Profissional;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private static final String URL = "jdbc:postgresql://localhost:5432/JonesPetShop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    // Método para adicionar um item
    public void adicionarItem(Item item) {
        String sql = "INSERT INTO Item (descricao, preco, tipo, validade, marca, modelo, equipamentos, quantidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, item.getDescricao());
            stmt.setDouble(2, item.getPreco());
            stmt.setString(3, item instanceof Produto ? "Produto" : "Servico");

            if (item instanceof Produto) {
                Produto produto = (Produto) item;
                stmt.setDate(4, Date.valueOf(produto.getValidade()));
                stmt.setString(5, produto.getMarca());
                stmt.setString(6, produto.getModelo());
                stmt.setNull(7, Types.VARCHAR); // Para "equipamentos"
                stmt.setInt(8, produto.getQuantidade());
            } else if (item instanceof Servico) {
                Servico servico = (Servico) item;
                stmt.setNull(4, Types.DATE); // Para "validade"
                stmt.setNull(5, Types.VARCHAR); // Para "marca"
                stmt.setNull(6, Types.VARCHAR); // Para "modelo"
                stmt.setString(7, servico.getEquipamentos());
                stmt.setInt(8, 1);
            }

            stmt.executeUpdate();

            // Obter o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                item.setId(generatedId); // Atualiza o ID do item

                // Adicionar associações de profissionais para serviços
                if (item instanceof Servico) {
                    Servico servico = (Servico) item;
                    adicionarProfissionaisServico(conn, generatedId, servico.getProfissional());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void adicionarProfissionaisServico(Connection conn, int servicoId, List<Profissional> profissionais) throws SQLException {
        String sql = "INSERT INTO servico_profissional (servico_id, profissional_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Profissional profissional : profissionais) {
                stmt.setInt(1, servicoId);
                stmt.setInt(2, profissional.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Método para obter um item por ID
    public Item getItemById(int id) {
        String sql = "SELECT * FROM Item WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToItem(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para mapear ResultSet para Item
    private Item mapResultSetToItem(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String descricao = rs.getString("descricao");
        double preco = rs.getDouble("preco");
        String tipo = rs.getString("tipo");

        if (tipo.equals("Produto")) {
            LocalDate validade = rs.getDate("validade").toLocalDate();
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            int quantidade = rs.getInt("quantidade");
            return new Produto(id, descricao, preco, validade, marca, modelo, quantidade);
        } else if (tipo.equals("Servico")) {
            String equipamentos = rs.getString("equipamentos");
            List<Profissional> profissionais = getProfissionaisByServicoId(id);
            return new Servico(id, descricao, preco, profissionais, equipamentos);
        }

        return null;
    }

    // Método para atualizar um item
    public void atualizarItem(Item item) {
        String sql = "UPDATE Item SET descricao = ?, preco = ?, tipo = ?, validade = ?, marca = ?, modelo = ?, equipamentos = ?, quantidade = ?, ativo = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getDescricao());
            stmt.setDouble(2, item.getPreco());
            stmt.setString(3, item instanceof Produto ? "Produto" : "Servico");

            if (item instanceof Produto) {
                Produto produto = (Produto) item;
                stmt.setDate(4, Date.valueOf(produto.getValidade()));
                stmt.setString(5, produto.getMarca());
                stmt.setString(6, produto.getModelo());
                stmt.setNull(7, Types.VARCHAR); // Para "equipamentos"
                stmt.setInt(8, produto.getQuantidade());
                stmt.setBoolean(9, produto.getAtivo());
            } else if (item instanceof Servico) {
                Servico servico = (Servico) item;
                stmt.setNull(4, Types.DATE); // Para "validade"
                stmt.setNull(5, Types.VARCHAR); // Para "marca"
                stmt.setNull(6, Types.VARCHAR); // Para "modelo"
                stmt.setString(7, servico.getEquipamentos());
                stmt.setInt(8, 1);
                stmt.setBoolean(9, servico.getAtivo());
                // Atualizar associações de profissionais
                atualizarProfissionaisServico(conn, servico.getId(), servico.getProfissional());
            }

            stmt.setInt(10, item.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para deletar um item
    public void deletarItem(int id) {
        String sql = "DELETE FROM Item WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Deletar associações de profissionais, se existir
            deletarProfissionaisServico(conn, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os serviços
    public List<Item> getAllServicos() {
        List<Item> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE tipo = 'Servico'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                servicos.add(mapResultSetToItem(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    public List<Item> getAllItens() {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT * FROM Item WHERE ativo = TRUE";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Item item = mapResultSetToItem(rs);

                // Se o item for um serviço, carregar os profissionais
                if (item instanceof Servico) {
                    Servico servico = (Servico) item;
                    List<Profissional> profissionais = buscarProfissionaisDoServico(servico.getId(), conn);
                    servico.setProfissional(profissionais); // Setar os profissionais
                }

                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itens;
    }
    
    private Profissional mapResultSetToProfissional(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String especialidade = rs.getString("especialidade");

        return new Profissional(id, nome, especialidade);
    }

    private List<Profissional> buscarProfissionaisDoServico(int servicoId, Connection conn) throws SQLException {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT p.* FROM Profissional p "
                + "JOIN Servico_Profissional sp ON p.id = sp.profissional_id "
                + "WHERE sp.servico_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Profissional profissional = mapResultSetToProfissional(rs);
                profissionais.add(profissional);
            }
        }
        return profissionais;
    }


    private void atualizarProfissionaisServico(Connection conn, int servicoId, List<Profissional> profissionais) throws SQLException {
        // Primeiro, deletar associações existentes
        deletarProfissionaisServico(conn, servicoId);
        // Depois, adicionar novas associações
        adicionarProfissionaisServico(conn, servicoId, profissionais);
    }

    private void deletarProfissionaisServico(Connection conn, int servicoId) throws SQLException {
        String sql = "DELETE FROM Servico_Profissional WHERE servico_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, servicoId);
            stmt.executeUpdate();
        }
    }
  

    // Método auxiliar para buscar profissionais associados a um serviço
    private List<Profissional> buscarProfissionaisPorServico(int servicoId) {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT p.* FROM Profissional p " +
                     "JOIN servico_profissional sp ON p.id = sp.profissional_id " +
                     "WHERE sp.servico_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String especialidade = rs.getString("especialidade");
                profissionais.add(new Profissional(id, nome, especialidade));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profissionais;
    }

    // Método auxiliar para obter profissionais de um serviço específico
    private List<Profissional> getProfissionaisByServicoId(int servicoId) {
        List<Profissional> profissionais = new ArrayList<>();
        String sql = "SELECT p.* FROM Profissional p JOIN Servico_Profissional sp ON p.id = sp.profissional_id WHERE sp.servico_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, servicoId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Profissional profissional = new Profissional(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especialidade")
                );
                profissionais.add(profissional);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profissionais;
    }
}

