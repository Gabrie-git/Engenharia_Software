package Financeiro.Controller;

import Financeiro.Model.Item;
import Servico.Model.Profissional;
import java.text.NumberFormat;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public class ProfissionaisTableModel1 extends AbstractTableModel {
    private final List<Profissional> profissionais;
    private final String[] colunas = {"ID","Nome", "Especialidade"};


    
    public ProfissionaisTableModel1(List<Profissional> profissional) {
        this.profissionais = profissional;
    }

    @Override
    public int getRowCount() {
        return profissionais.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profissional profissional = profissionais.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return profissional.getNome();
            case 2:
                return profissional.getEspecialidade();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}