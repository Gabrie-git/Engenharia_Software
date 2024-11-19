package Atendimento.Controller;

import Atendimento.Model.Animal;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AnimaisTableModel extends AbstractTableModel {
    private final String[] colunas = {"ID", "Nome", "Data de Nascimento", "Raça", "Dono", "CPF/CNPJ - Dono"};
    private final List<Animal> animais;

    // Formato desejado para a data de nascimento
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AnimaisTableModel(List<Animal> animais) {
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
            case 2: 
                // Formata a data de nascimento antes de retornar
                return animal.getDataNascimento() != null ? animal.getDataNascimento().format(dateFormatter) : "";
            case 3: return animal.getRaca();
            case 4: return animal.getNomeDono(); 
            case 5: return animal.getCpfDono();// Utiliza o método getCpfDono para obter CPF ou CNPJ
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
    public Animal getAnimalAt(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < animais.size()) {
            return animais.get(rowIndex);
        }
        return null;
    }
}
