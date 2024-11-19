
package Financeiro.Model;

import Servico.Model.Profissional;
 import java.util.List;

 public class Servico extends Item {
     
    private String equipamentos;
    private List<Profissional> profissionais;
    private int quantidade;
    
    public Servico(int id, String descricao, double preco, List<Profissional> profissional, String equipamento) {
        super(id, descricao, preco);
        this.equipamentos = equipamento;
        this.profissionais = profissional;
        this.quantidade = 1;
    }
    
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public String getEquipamentos() {
        return equipamentos;
    }
    
    public List<Profissional> getProfissional() {
        return profissionais;
    }
    
    public void setProfissional(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }
    
    public void setEquipamentos(String equipamentos) {
        this.equipamentos = equipamentos;
    }
    
 }
