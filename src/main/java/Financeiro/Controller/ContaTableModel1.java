package Financeiro.Controller;

import Financeiro.Model.Conta;
import Financeiro.Model.Item;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ContaTableModel1 extends AbstractTableModel {
    private final List<Conta> contas;
    private final String[] colunas = {"ID", "Itens", "Cliente", "Data", "Total", "Status"};


    
    public ContaTableModel1(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public int getRowCount() {
        return contas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Conta conta = contas.get(rowIndex);

        NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String valor = formatador.format(conta.getTotal());
        String nomesString = conta.getItens().stream().map(Item::getDescricao).collect(Collectors.joining(", "));
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = conta.getData().format(formato);
        switch (columnIndex) {
            case 0:
                return conta.getId();
            case 1:
                return nomesString;
            case 2:
                return conta.getCliente().getNome();
            case 3:
                return dataFormatada;
            case 4:
                return valor;
            case 5:
                return conta.getStatusPagamento();
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}