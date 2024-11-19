package Atendimento.Controller;

import Atendimento.DAO.ClienteDAO;
import Atendimento.Model.Cliente;
import Atendimento.Model.Fisica;
import java.util.List;

public class GerenciaFisica {

    private ClienteDAO clienteDAO;

    public GerenciaFisica() {
        this.clienteDAO = new ClienteDAO();
    }

    public List<Cliente> getAllFisica() {
        return clienteDAO.listarTodosClientes();
    }

    public boolean addFisica(Fisica cliente) {
        Cliente c = (Cliente) cliente;
        clienteDAO.adicionarCliente(c);
        return true;
    }

    public Cliente getFisicaById(int id) {
        return clienteDAO.buscarClientePorId(id);
    }

    public boolean updateFisica(int id, Fisica novoProduto) {
        Cliente aux = getFisicaById(id);
        if (aux instanceof Fisica) {
            Fisica conta = (Fisica) aux;
            conta.setNome(novoProduto.getNome());
            conta.setLogradouro(novoProduto.getLogradouro());
            conta.setNumero(novoProduto.getNumero());
            conta.setComplemento(novoProduto.getComplemento());
            conta.setBairro(novoProduto.getBairro());
            conta.setMunicipio(novoProduto.getMunicipio());
            conta.setEstado(novoProduto.getEstado());
            conta.setTelefone(novoProduto.getTelefone());
            conta.setAnimais(novoProduto.getAnimais());
            conta.setCpf(novoProduto.getCpf());
            clienteDAO.atualizarCliente(conta);
        }
        return true;
    }

    public boolean deleteFisica(int id) {
        clienteDAO.removerCliente(id);
        return true;
    }
}
