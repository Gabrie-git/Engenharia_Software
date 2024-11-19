package Financeiro.Controller;

import Financeiro.Model.Item;
import Financeiro.Model.Servico;
import Servico.Model.Profissional;
import java.text.NumberFormat;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ServicoTableModel111 extends AbstractTableModel {
    private final List<Item> itens;
    private final String[] colunas = {"ID", "Descrição", "Preço", "Profissionais", "Equipamentos"};


    
    public ServicoTableModel111(List<Item> item) {
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
        if(item instanceof Servico){
            Servico aux = (Servico) item;
            NumberFormat formatador = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String valor = formatador.format(aux.getPreco());
            String nomesString = aux.getProfissional().stream().map(Profissional::getNome).collect(Collectors.joining(", "));
            
            switch (columnIndex) {
                case 0:
                    return aux.getId();
                case 1:
                    return aux.getDescricao();
                case 2:
                    return valor;
                case 3:
                    return nomesString; 
                case 4:
                    return aux.getEquipamentos();
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