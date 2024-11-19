package Atendimento.Model;

import Servico.Model.Agendamento;
import java.time.LocalDate;
import java.util.ArrayList;

public class Agenda {
    private ArrayList<Agendamento> agendamentos;
    private LocalDate data;

    public Agenda() {
        this.agendamentos = new ArrayList<>();
    }

    public Agenda(LocalDate data) {
        this.data = data;
        this.agendamentos = new ArrayList<>();
    }

    // Getter para a data
    public LocalDate getData() {
        return data;
    }

    // Setter para a data
    public void setData(LocalDate data) {
        this.data = data;
    }

    // Getter para a lista de agendamentos
    public ArrayList<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    // Adiciona um agendamento à lista
    public void addAgendamento(Agendamento agendamento) {
        this.agendamentos.add(agendamento);
    }

    // Remove um agendamento da lista
    public void removeAgendamento(Agendamento agendamento) {
        this.agendamentos.remove(agendamento);
    }

    // Método para verificar se há agendamentos na data especificada
    public boolean possuiAgendamentos() {
        return !this.agendamentos.isEmpty();
    }

    // Método para buscar agendamentos de um cliente específico
    public ArrayList<Agendamento> buscaAgendamentosPorCliente(Cliente cliente) {
        ArrayList<Agendamento> agendamentosCliente = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getCliente().equals(cliente)) {
                agendamentosCliente.add(agendamento);
            }
        }
        return agendamentosCliente;
    }

    // Método para buscar agendamentos de um animal específico
    public ArrayList<Agendamento> buscaAgendamentosPorAnimal(Animal animal) {
        ArrayList<Agendamento> agendamentosAnimal = new ArrayList<>();
        for (Agendamento agendamento : agendamentos) {
            if (agendamento.getAnimal().equals(animal)) {
                agendamentosAnimal.add(agendamento);
            }
        }
        return agendamentosAnimal;
    }

    // Método para buscar um cliente pelo CPF ou CNPJ
    public Cliente getCliente(String identificador) {
        for (Agendamento agendamento : agendamentos) {
            Cliente cliente = agendamento.getCliente();
            if (cliente instanceof Fisica && ((Fisica) cliente).getCpf().equals(identificador)) {
                return cliente;
            } else if (cliente instanceof Juridica && ((Juridica) cliente).getCnpj().equals(identificador)) {
                return cliente;
            }
        }
        return null;
    }

    // Método para buscar um animal pelo nome
    public Animal getAnimal(String nome) {
        for (Agendamento agendamento : agendamentos) {
            Animal animal = agendamento.getAnimal();
            if (animal.getNome().equals(nome)) {
                return animal;
            }
        }
        return null;
    }

    // Imprime os detalhes da agenda
    public void imprimeAgenda() {
        System.out.println("Data: " + this.data);
        System.out.println("Agendamentos:");
        for (Agendamento agendamento : agendamentos) {
            System.out.println(agendamento);
        }
    }
}
