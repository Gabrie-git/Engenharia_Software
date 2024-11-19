package Atendimento.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Animal {

    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String raca;
    private float peso;
    private float altura;
    private Cliente dono; // Adiciona o atributo para o dono
    private byte[] imagem; // Imagem do animal em formato de array de bytes
    private boolean ativo;

    // Construtor completo (com imagem)
    public Animal(int id, String nome, LocalDate dataNascimento, String raca, float peso, float altura, Cliente dono, byte[] imagem) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
        this.peso = peso;
        this.altura = altura;
        this.dono = dono;
        this.imagem = imagem;
        this.ativo = true;
    }

    // Construtor sem imagem (imagem padrão como array de bytes vazio)
    public Animal(int id, String nome, LocalDate dataNascimento, String raca, float peso, float altura, Cliente dono) {
        this(id, nome, dataNascimento, raca, peso, altura, dono, new byte[0]); // Imagem padrão vazia
    }

    // Construtor vazio
    public Animal() {
        
    }

    // Getter e Setter para o atributo dono
    public Cliente getDono() {
        return dono;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public String getNomeDono() {
        return dono.getNome();
    }
    // Método para obter o CPF ou CNPJ do dono
    public String getCpfDono() {
        if (dono instanceof Fisica) {
            return ((Fisica) dono).getCpf();
        } else if (dono instanceof Juridica) {
            return ((Juridica) dono).getCnpj();
        }
        return null;
    }
    
    
    // Formato de data esperado
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public boolean setDataNascimento(String data) {
        try {
            this.dataNascimento = LocalDate.parse(data, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            System.err.println("Erro: Formato de data inválido. Use dd/MM/yyyy.");
            return false;
        }
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    @Override
    public String toString() {
        return this.getNome();
    }
    
}
