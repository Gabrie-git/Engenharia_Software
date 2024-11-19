package Servico.view;

import Servico.Controller.GerenciaProfissional;
import Servico.Model.Profissional;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProfissionalTableModel extends AbstractTableModel {
    private final List<Profissional> profissionais;
    private final String[] colunas = {"ID","Nome", "Especialidade"};

    private GerenciaProfissional gerenciaProfissional = new GerenciaProfissional();
    
    public ProfissionalTableModel(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public ProfissionalTableModel() {
        profissionais = gerenciaProfissional.listarProfissionais();
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
                return profissional.getId();
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