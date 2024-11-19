package Atendimento.Controller;

import Atendimento.DAO.ClienteDAO;
import Atendimento.Model.Cliente;
import Atendimento.Model.Juridica;
import java.util.List;

public class GerenciaJuridica {

    private ClienteDAO clienteDAO;

    public GerenciaJuridica() {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> getAllJuridica() {
        // Filtra apenas clientes do tipo Juridica
        List<Cliente> todosClientes = clienteDAO.listarTodosClientes();
        todosClientes.removeIf(cliente -> !(cliente instanceof Juridica));
        return todosClientes;
    }

    public boolean addJuridica(Juridica cliente) {
        clienteDAO.adicionarCliente(cliente);
        return true;
    }

    // Read
    public Cliente getJuridicaById(int id) {
        Cliente cliente = clienteDAO.buscarClientePorId(id);
        return cliente;
    }

    // Update
    public boolean updateJuridica(int id, Juridica novoProduto) {
        Cliente aux = getJuridicaById(id);
        if (aux instanceof Juridica) {
            Juridica conta = (Juridica) aux;
            conta.setNome(novoProduto.getNome());
            conta.setLogradouro(novoProduto.getLogradouro());
            conta.setNumero(novoProduto.getNumero());
            conta.setComplemento(novoProduto.getComplemento());
            conta.setBairro(novoProduto.getBairro());
            conta.setMunicipio(novoProduto.getMunicipio());
            conta.setEstado(novoProduto.getEstado());
            conta.setTelefone(novoProduto.getTelefone());
            conta.setAnimais(novoProduto.getAnimais());
            conta.setCnpj(novoProduto.getCnpj());
            
            clienteDAO.atualizarCliente(conta);
            return true;
        }
        return false;
    }

    // Delete
    public boolean deleteJuridica(int id) {
        Cliente cliente = getJuridicaById(id);
        if (cliente != null && cliente instanceof Juridica) {
            clienteDAO.removerCliente(id);
            return true;
        }
        return false;
    }
}
