package Atendimento.Controller;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import Atendimento.Model.Animal;

public class AnimaisClienteTableModel extends AbstractTableModel {
    private final List<Animal> animais;
    private final String[] colunas = {"ID", "Nome", "Espécie", "Raça"}; // Ajuste conforme necessário

    public AnimaisClienteTableModel(List<Animal> animais) {
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
            case 0:
                return animal.getId();
            case 1:
                return animal.getNome();
            case 2:
                return animal.getPeso(); // Ajuste conforme o atributo correto
            case 3:
                return animal.getRaca();    // Ajuste conforme o atributo correto
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
}