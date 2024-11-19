package Servico.Controller;

import Servico.Model.Agendamento;
import Servico.DAO.AgendamentoDAO;
import java.util.List;

public class GerenciaAgendamento {
    private AgendamentoDAO agendamentoDAO;

    public GerenciaAgendamento() {
        this.agendamentoDAO = new AgendamentoDAO();
    }

    public List<Agendamento> getAllAgendamentos() {
        return agendamentoDAO.listarTodosAgendamentos();
    }

    public void cadastrarAgendamento(Agendamento agendamento) {
        agendamentoDAO.adicionarAgendamento(agendamento);
    }

    public void alterarAgendamento(int id, Agendamento novoAgendamento) {
        Agendamento agendamento = agendamentoDAO.buscarAgendamento(id);
        if (agendamento != null) {
            agendamento.setAnimal(novoAgendamento.getAnimal());
            agendamento.setCliente(novoAgendamento.getCliente());
            agendamento.setServico(novoAgendamento.getServico());
            agendamento.setHora(novoAgendamento.getHora());
            agendamento.setData(novoAgendamento.getData());
            agendamento.setProfissional(novoAgendamento.getProfissional());
            agendamento.setStatus(novoAgendamento.getStatus());

            agendamentoDAO.atualizarAgendamento(agendamento);
        }
    }

    public void listarAgendamentos() {
        List<Agendamento> agendamentos = agendamentoDAO.listarTodosAgendamentos();
        if (agendamentos.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
        } else {
            for (Agendamento agendamento : agendamentos) {
                System.out.println(agendamento);
            }
        }
    }

    public void removerAgendamento(int id) {
        agendamentoDAO.removerAgendamento(id);
    }

    public Agendamento buscarAgendamento(int id) {
        return agendamentoDAO.buscarAgendamento(id);
    }
}
