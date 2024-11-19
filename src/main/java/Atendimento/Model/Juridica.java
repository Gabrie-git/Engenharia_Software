package Atendimento.Model;

import java.util.List;

public class Juridica extends Cliente {
    private String cnpj;

    public Juridica() {
        super();
    }
    
    // Construtor
    public Juridica(int id, String nome, String logradouro, int numero, String complemento, 
                    String bairro, String municipio, String estado, String telefone, 
                    List<Animal> animais, String cnpj) {
        super(id, nome, logradouro, numero, complemento, bairro, municipio, estado, telefone, animais);
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
