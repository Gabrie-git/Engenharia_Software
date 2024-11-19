
package Atendimento.DAO;


import Atendimento.Model.Animal;
import Atendimento.Model.Cliente;
import Atendimento.Model.Fisica;
import Atendimento.Model.Juridica;
import BD.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Connection conexao;
    private AnimalDAO animalDAO;

    public ClienteDAO() {
        this.conexao = Conexao.conectar();
        this.animalDAO = new AnimalDAO();
    }

    // Create - Inserir um cliente (Física ou Jurídica)
    public void adicionarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, tipo_cliente, cpf, cnpj) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setInt(3, cliente.getNumero());
            stmt.setString(4, cliente.getComplemento());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getMunicipio());
            stmt.setString(7, cliente.getEstado());
            stmt.setString(8, cliente.getTelefone());

            if (cliente instanceof Fisica) {
                stmt.setString(9, "Fisica");
                stmt.setString(10, ((Fisica) cliente).getCpf());
                stmt.setNull(11, Types.VARCHAR);
            } else if (cliente instanceof Juridica) {
                stmt.setString(9, "Juridica");
                stmt.setNull(10, Types.VARCHAR);
                stmt.setString(11, ((Juridica) cliente).getCnpj());
            }

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1)); // Atualiza o ID gerado no objeto cliente
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao adicionar cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Read - Buscar todos os clientes
    public List<Cliente> listarTodosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente WHERE ativo = TRUE";

        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Mapeia os dados do cliente
                Cliente cliente = mapResultSetToCliente(rs);

                // Carrega os animais associados a esse cliente
                List<Animal> animais = buscarAnimaisPorClienteId(cliente.getId());
                cliente.setAnimais(animais);

                // Adiciona o cliente à lista
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao listar clientes: " + ex.getMessage());
            ex.printStackTrace();
        }

        return clientes;
    }
    
    public List<Animal> buscarAnimaisPorClienteId(int clienteId) {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT * FROM Animal WHERE dono_id = ?";
        Cliente dono = buscarClientePorId(clienteId);
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Cria um novo objeto Animal e preenche os dados
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setNome(rs.getString("nome"));
                animal.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                animal.setRaca(rs.getString("raca"));
                animal.setPeso(rs.getFloat("peso"));
                animal.setAltura(rs.getFloat("altura"));
                animal.setDono(dono);
                // Atribua mais campos conforme necessário
                animais.add(animal);
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao buscar animais: " + ex.getMessage());
            ex.printStackTrace();
        }

        return animais;
    }



    // Read - Buscar um cliente pelo ID
    public Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCliente(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    // Update - Atualizar um cliente
    public void atualizarCliente(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, logradouro = ?, numero = ?, complemento = ?, bairro = ?, municipio = ?, estado = ?, telefone = ?, cpf = ?, cnpj = ? , ativo = ? WHERE id = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getLogradouro());
            stmt.setInt(3, cliente.getNumero());
            stmt.setString(4, cliente.getComplemento());
            stmt.setString(5, cliente.getBairro());
            stmt.setString(6, cliente.getMunicipio());
            stmt.setString(7, cliente.getEstado());
            stmt.setString(8, cliente.getTelefone());

            if (cliente instanceof Fisica) {
                stmt.setString(9, ((Fisica) cliente).getCpf());
                stmt.setNull(10, Types.VARCHAR); // Define CNPJ como nulo
            } else if (cliente instanceof Juridica) {
                stmt.setNull(9, Types.VARCHAR); // Define CPF como nulo
                stmt.setString(10, ((Juridica) cliente).getCnpj());
            } else {
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.VARCHAR);
            }
            stmt.setBoolean(11, cliente.getAtivo());
            stmt.setInt(12, cliente.getId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            } else {
                System.out.println("Nenhum cliente foi encontrado com o ID fornecido.");
            }

        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Delete - Remover um cliente
    public void removerCliente(int id) {
        String sql = "DELETE FROM Cliente WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao remover cliente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    public void removerClientePreservandoConta(int clienteId) {
    // Desvincula a conta associada ao cliente (seta o cliente_id para NULL)
    String sqlConta = "UPDATE conta SET cliente_id = NULL WHERE cliente_id = ?";
    String sqlCliente = "DELETE FROM cliente WHERE id = ?";

    try (PreparedStatement stmtConta = conexao.prepareStatement(sqlConta);
         PreparedStatement stmtCliente = conexao.prepareStatement(sqlCliente)) {

        // Desvincula a conta associada
        stmtConta.setInt(1, clienteId);
        stmtConta.executeUpdate();

        // Remove o cliente
        stmtCliente.setInt(1, clienteId);
        stmtCliente.executeUpdate();

        System.out.println("Cliente removido e conta preservada com sucesso!");

    } catch (SQLException e) {
        System.out.println("Erro ao remover o cliente ou desvincular a conta: " + e.getMessage());
        e.printStackTrace();
    }
}

    
    
    // Método auxiliar para mapear o ResultSet para um objeto Cliente
    private Cliente mapResultSetToCliente(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String logradouro = rs.getString("logradouro");
        int numero = rs.getInt("numero");
        String complemento = rs.getString("complemento");
        String bairro = rs.getString("bairro");
        String municipio = rs.getString("municipio");
        String estado = rs.getString("estado");
        String telefone = rs.getString("telefone");
        String tipoCliente = rs.getString("tipo_cliente");
        List<Animal> animais = animalDAO.buscarAnimaisPorClienteId(id);  // Busca os animais associados ao cliente

        Cliente cliente;

        if ("Fisica".equalsIgnoreCase(tipoCliente)) {
            String cpf = rs.getString("cpf");
            cliente = new Fisica(id, nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, animais, cpf);
        } else if ("Juridica".equalsIgnoreCase(tipoCliente)) {
            String cnpj = rs.getString("cnpj");
            cliente = new Juridica(id, nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, animais, cnpj);
        } else {
            // Caso tipo de cliente não seja reconhecido
            cliente = null;
        }

        return cliente;
    }
}

