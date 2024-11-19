package Financeiro.DAO;

import Financeiro.Model.Conta;
import Financeiro.Model.Item;
import Atendimento.Model.Cliente;
import Atendimento.DAO.ClienteDAO;
import BD.Conexao;
import Financeiro.Model.Produto;
import Financeiro.Model.Servico;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {
    private Connection conexao;

    public ContaDAO() {
        conexao = Conexao.conectar();
    }

    public boolean adicionarConta(Conta conta, int agen) {
        String sqlConta = "INSERT INTO conta (cliente_id, data, total, status_pagamento) VALUES (?, ?, ?, ?) RETURNING id";
        String sqlItens = "INSERT INTO conta_itens (conta_id, item_id, quantidade) VALUES (?, ?, ?)"; // Adicionada a coluna quantidade

        try (PreparedStatement stmtConta = conexao.prepareStatement(sqlConta); PreparedStatement stmtItens = conexao.prepareStatement(sqlItens)) {

            // Inserir dados na tabela conta
            stmtConta.setInt(1, conta.getCliente().getId());
            stmtConta.setDate(2, Date.valueOf(conta.getData()));
            stmtConta.setDouble(3, conta.getTotal());
            stmtConta.setString(4, conta.getStatusPagamento());

            // Use executeQuery para obter o ID gerado da conta
            try (ResultSet rsConta = stmtConta.executeQuery()) {
                if (rsConta.next()) {
                    int contaId = rsConta.getInt(1); // Obtém o ID gerado da conta

                    // Inserir itens relacionados à conta
                    for (Item item : conta.getItens()) {
                        stmtItens.setInt(1, contaId); // Conta ID
                        stmtItens.setInt(2, item.getId()); // Item ID
                        if (item instanceof Produto) {
                            Produto p = (Produto)item;
                            stmtItens.setInt(3, p.getQuantidade()); // Quantidade do item para essa conta
                        } else {
                            stmtItens.setInt(3, 1); // Quantidade do item para essa conta
                        }
                        
                        stmtItens.addBatch();
                    }

                    // Executa o batch de inserções de itens
                    stmtItens.executeBatch();

                    // Vincular à agenda se necessário
                    if (agen != -1) {
                        vincularContaAgendamento(contaId, agen);
                    }

                    return true;
                } else {
                    throw new SQLException("Nenhum ID foi retornado ao inserir a conta.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Conta buscarContaPorId(int id) {
        String sql = "SELECT * FROM conta WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int clienteId = rs.getInt("cliente_id");
                LocalDate data = rs.getDate("data").toLocalDate();
                double total = rs.getDouble("total");
                String statusPagamento = rs.getString("status_pagamento");

                Cliente cliente = new ClienteDAO().buscarClientePorId(clienteId);
                List<Item> itens = buscarItensDaConta(id);

                return new Conta(id, itens, cliente, data, total, statusPagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Conta> buscarTodasContas() {
        List<Conta> contas = new ArrayList<>();
        String sql = "SELECT * FROM conta";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int clienteId = rs.getInt("cliente_id");
                LocalDate data = rs.getDate("data").toLocalDate();
                double total = rs.getDouble("total");
                String statusPagamento = rs.getString("status_pagamento");

                Cliente cliente = new ClienteDAO().buscarClientePorId(clienteId);
                List<Item> itens = buscarItensDaConta(id);

                Conta conta = new Conta(id, itens, cliente, data, total, statusPagamento);
                contas.add(conta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }

    public boolean atualizarConta(int id, Conta novaConta) {
        String sqlConta = "UPDATE conta SET cliente_id = ?, data = ?, total = ?, status_pagamento = ? WHERE id = ?";
        String sqlDeleteItens = "DELETE FROM conta_itens WHERE conta_id = ?";
        String sqlInsertItens = "INSERT INTO conta_itens (conta_id, item_id, quantidade) VALUES (?, ?, ?) ON CONFLICT (conta_id, item_id) DO NOTHING";

        try (PreparedStatement stmtConta = conexao.prepareStatement(sqlConta);
             PreparedStatement stmtDeleteItens = conexao.prepareStatement(sqlDeleteItens);
             PreparedStatement stmtInsertItens = conexao.prepareStatement(sqlInsertItens)) {

            // Atualizar dados da tabela contas
            stmtConta.setInt(1, novaConta.getCliente().getId());
            stmtConta.setDate(2, Date.valueOf(novaConta.getData()));
            stmtConta.setDouble(3, novaConta.getTotal());
            stmtConta.setString(4, novaConta.getStatusPagamento());
            stmtConta.setInt(5, id);
            stmtConta.executeUpdate();

            // Remover todos os itens associados à conta
            stmtDeleteItens.setInt(1, id);
            stmtDeleteItens.executeUpdate();

            // Inserir os novos itens
            for (Item item : novaConta.getItens()) {

                stmtInsertItens.setInt(1, id); // Conta ID
                stmtInsertItens.setInt(2, item.getId()); // Item ID
                if (item instanceof Produto) {
                    Produto p = (Produto) item;
                    stmtInsertItens.setInt(3, p.getQuantidade()); // Quantidade do item para essa conta
                } else {
                    stmtInsertItens.setInt(3, 1); // Quantidade do item para essa conta
                }
                stmtInsertItens.addBatch();
            }
            stmtInsertItens.executeBatch();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deletarConta(int id) {
        String sqlItens = "DELETE FROM conta_itens WHERE conta_id = ?";
        String sqlConta = "DELETE FROM conta WHERE id = ?";
        try (PreparedStatement stmtItens = conexao.prepareStatement(sqlItens);
             PreparedStatement stmtConta = conexao.prepareStatement(sqlConta)) {

            // Remover itens da conta
            stmtItens.setInt(1, id);
            stmtItens.executeUpdate();

            // Remover a conta
            stmtConta.setInt(1, id);
            stmtConta.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Item> buscarItensDaConta(int contaId) {
        List<Item> itens = new ArrayList<>();
        String sql = "SELECT item_id, quantidade FROM conta_itens WHERE conta_id = ?"; // Consulta agora inclui a quantidade

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            ResultSet rs = stmt.executeQuery();

            ItemDAO itemDAO = new ItemDAO();
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                int quantidade = rs.getInt("quantidade"); // Obtém a quantidade

                // Busca o item no banco de dados pelo itemId
                Item item = itemDAO.getItemById(itemId);
                if (item != null) {
                    if(item instanceof Produto){
                        Produto p = (Produto) item;
                        p.setQuantidade(quantidade); // Define a quantidade correta no item
                        itens.add(p);
                    }else{
                        Servico s = (Servico) item;
                        itens.add(s);
                    }
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }

    
    public void vincularContaAgendamento(int contaId, int agendamentoId) {
        String sql = "INSERT INTO conta_agendamento (conta_id, agendamento_id) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, contaId);
            stmt.setInt(2, agendamentoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
