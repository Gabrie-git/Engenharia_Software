package Financeiro.Controller;

import Financeiro.Model.Item;
import Financeiro.Model.Produto;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;

public class ProdutoTableModel11 extends AbstractTableModel {
    private final List<Item> itens;
    private final String[] colunas = {"ID", "Descrição", "Preço", "Validade", "Marca", "Modelo", "Quantidade"};


    
    public ProdutoTableModel11(List<Item> item) {
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
        if(item instanceof Produto){
            Produto aux = (Produto) item;
            NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String valor = formatador.format(aux.getPreco());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = aux.getValidade().format(formato);
            switch (columnIndex) {
                case 0:
                    return aux.getId();
                case 1:
                    return aux.getDescricao();
                case 2:
                    return valor;
                case 3:
                    return dataFormatada;
                case 4:
                    return aux.getMarca();
                case 5:
                    return aux.getModelo();
                case 6:
                    return aux.getQuantidade();
                default:
                    return null;
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }
    
}