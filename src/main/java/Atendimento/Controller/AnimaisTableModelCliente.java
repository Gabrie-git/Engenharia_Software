package Atendimento.Controller;

import Atendimento.Model.Animal;
import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnimaisTableModelCliente extends AbstractTableModel {

    private final String[] colunas = {"ID", "Nome", "Data de Nascimento", "Raça", "Peso", "Altura", "CPF do Dono"};
    private List<Animal> animais;

    // Formato de data para exibição
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AnimaisTableModelCliente(List<Animal> animais) {
        this.animais = animais;
    }

    @Override
    public int getRowCount() {
        return animais.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = animais.get(rowIndex);
        switch (columnIndex) {
            case 0: return animal.getId();
            case 1: return animal.getNome();
            case 2: return animal.getDataNascimento().format(dateFormatter);
            case 3: return animal.getRaca();
            case 4: return animal.getPeso();
            case 5: return animal.getAltura();
            case 6: return animal.getCpfDono();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    // Atualiza a lista de animais e notifica a tabela sobre a mudança
    public void atualizarDados(List<Animal> novosAnimais) {
        this.animais = novosAnimais;
        fireTableDataChanged(); // Notifica a tabela sobre as mudanças
    }
}
