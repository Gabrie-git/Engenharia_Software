package Financeiro.Model;

import Atendimento.Model.Cliente;
import java.time.LocalDate;
import java.util.List;

public class Conta {
    private int id;
    private List<Item> itens; // Lista de itens (produtos ou serviços)
    private Cliente cliente; 
    private LocalDate data;
    private double total;
    private String statusPagamento; // Status do pagamento (ex: "Pago", "Pendente")

    public Conta(int id, List<Item> itens, Cliente cliente, LocalDate data, double total, String statusPagamento) {
        this.id = id;
        this.itens = itens;
        this.cliente = cliente;
        this.data = data;
        this.total = total;
        this.statusPagamento = statusPagamento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public List<Item> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public double getTotal() {
        return total;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", data=" + data +
                ", total=" + total +
                ", statusPagamento='" + statusPagamento + '\'' +
                '}';
    }
}
