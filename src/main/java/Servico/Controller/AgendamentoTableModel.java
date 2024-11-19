package Servico.Controller;

import Servico.Model.Agendamento;
import java.time.format.DateTimeFormatter;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AgendamentoTableModel extends AbstractTableModel {
    private final List<Agendamento> agendamentos;
    private final String[] colunas = {"ID", "Animal", "Serviço", "Data", "Hora", "Status"};


    
    public AgendamentoTableModel(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @Override
    public int getRowCount() {
        return agendamentos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Agendamento agenda = agendamentos.get(rowIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaString = agenda.getHora().format(formatter);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = agenda.getData().format(formato);
        switch (columnIndex) {
            case 0:
                return agenda.getId();
            case 1:
                return agenda.getAnimal().getNome();
            case 2:
                return agenda.getServico().getDescricao();
            case 3:
                return dataFormatada;
            case 4:
                return horaString;
            case 5:
                return agenda.getStatus();    
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}