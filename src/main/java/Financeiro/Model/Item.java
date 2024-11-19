
package Financeiro.Model;

public abstract class Item {
    private int id;
    private String descricao;
    private double preco;
    private boolean ativo;
    public Item(int id, String descricao, double preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = true;
    }
    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getPreco() {
        return preco;
    }
    public void setId(int id) {
        this.id = id;
    }
    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    @Override
    public String toString() {
        return this.getDescricao();
    }
 }
