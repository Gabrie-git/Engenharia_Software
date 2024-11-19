package Financeiro.Controller;

import Financeiro.DAO.ContaDAO;
import Financeiro.Model.Conta;
import java.util.ArrayList;
import java.util.List;


public class GerenciaConta {

    private List<Conta> contas;
    private ContaDAO contaDAO;

    public GerenciaConta() {
        this.contas = new ArrayList<>();
        this.contaDAO = new ContaDAO();
        this.contas = contaDAO.buscarTodasContas(); // Carrega todas as contas do banco ao inicializar
    }

    public boolean addConta(Conta conta) {
        boolean adicionada = contaDAO.adicionarConta(conta , -1);
        if (adicionada) {
            contas.add(conta);
        }
        return adicionada; // Conta adicionada com sucesso
    }

    public Conta buscarContaPorId(int id) {
        return contaDAO.buscarContaPorId(id); // Busca conta no banco de dados
    }

    public List<Conta> getAllContas() {
        return new ArrayList<>(contas); // Retorna uma cópia da lista de contas
    }

    public boolean updateConta(int id, Conta novaConta) {
        boolean atualizada = contaDAO.atualizarConta(id, novaConta);
        if (atualizada) {
            Conta contaExistente = buscarContaPorId(id);
            if (contaExistente != null) {
                contaExistente.setItens(novaConta.getItens());
                contaExistente.setCliente(novaConta.getCliente());
                contaExistente.setData(novaConta.getData());
                contaExistente.setTotal(novaConta.getTotal());
                contaExistente.setStatusPagamento(novaConta.getStatusPagamento());
            }
        }
        return atualizada;
    }

    public boolean deleteConta(int id) {
        boolean deletada = contaDAO.deletarConta(id);
        if (deletada) {
            contas.removeIf(c -> c.getId() == id);
        }
        return deletada;
    }
}
