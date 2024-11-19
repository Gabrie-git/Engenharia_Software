package Atendimento.Model;

import java.util.List;

public class Fisica extends Cliente {
    private String cpf;

    public Fisica() {
        super();
    }
    
    // Construtor
    public Fisica(int id, String nome, String logradouro, int numero, String complemento, 
                  String bairro, String municipio, String estado, String telefone, 
                  List<Animal> animais, String cpf) {
        super(id, nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, animais);
        this.cpf = cpf;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
