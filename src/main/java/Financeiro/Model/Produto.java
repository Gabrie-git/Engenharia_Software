
package Financeiro.Model;

import java.time.LocalDate;
 public class Produto extends Item {
    private LocalDate validade;
    private String marca;
    private String modelo;
    private int quantidade;
    
    public Produto(int id, String descricao, double preco, LocalDate validade, String marca, String modelo, int quantidade) {
        super(id, descricao, preco);
        this.validade = validade;
        this.marca = marca;
        this.modelo = modelo;
        this.quantidade = quantidade;
    }
    
    public LocalDate getValidade() {
        return validade;
    }
    public String getMarca() {
        return marca;
    }
    public String getModelo() {
        return modelo;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
 }