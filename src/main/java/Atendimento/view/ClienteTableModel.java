package Atendimento.view;

import Servico.view.*;
import Servico.Controller.GerenciaProfissional;
import Servico.Model.Profissional;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {
    private final List<Profissional> profissionais;
    private final String[] colunas = {"Nome", "Especialidade"};

    private GerenciaProfissional gerenciaProfissional = new GerenciaProfissional();
    
    public ClienteTableModel(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    public ClienteTableModel() {
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
                return profissional.getNome();
            case 1:
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