package Servico.Model;

import Atendimento.Model.Animal;
import Atendimento.Model.Cliente;
import Financeiro.Model.Servico;
import java.time.LocalDate;
import java.time.LocalTime;

public class Agendamento {
    private Animal animal;
    private Cliente cliente;
    private Servico servico;
    private LocalTime hora;
    private LocalDate data;
    private Profissional profissional;
    private String status;
    private int id;

    // Construtor
    public Agendamento(int id, Animal animal, Cliente cliente, Servico servico, LocalTime hora, LocalDate data, Profissional profissional, String status){
        this.id = id;
        this.animal = animal;
        this.cliente = cliente;
        this.servico = servico;
        this.hora = hora;
        this.data = data;
        this.profissional = profissional;
        this.status = status;
    }

    // Getters e Setters para os atributos

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }
    
    public String getStatus() {
        return status;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public LocalDate getData() {
        return data;
    }
        
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }
}