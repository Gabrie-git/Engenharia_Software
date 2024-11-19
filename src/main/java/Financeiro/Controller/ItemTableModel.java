package Financeiro.Controller;

import Financeiro.Model.Item;
import Financeiro.Model.Produto;
import Financeiro.Model.Servico;
import java.text.NumberFormat;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public class ItemTableModel extends AbstractTableModel {
    private final List<Item> itens;
    private final String[] colunas = {"ID","Descrição", "Preço", "Quantidade"};


    
    public ItemTableModel(List<Item> item) {
        this.itens = item;
    }

    @Override
    public int getRowCount() {
        return itens.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Item item = itens.get(rowIndex);
        int aux;
        if(item instanceof Servico){
            aux = 1;
        }else{
            Produto prod = (Produto)item;
            aux = prod.getQuantidade();
        }
        NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String valor = formatador.format(item.getPreco());
        switch (columnIndex) {
            case 0:
                return rowIndex;
            case 1:
                return item.getDescricao();
            case 2:
                return valor;
            case 3:
                return aux;
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}