package Atendimento.Controller;

import Atendimento.Model.Cliente;
import Atendimento.Model.Fisica;
import Atendimento.Model.Juridica;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClientesTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    private final String[] columnNames = {"ID", "Nome", "Logradouro", "Número", "CPF/CNPJ", "Telefone"};

    public ClientesTableModel(List<Cliente> clientes) {
        this.clientes = clientes != null ? clientes : new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return cliente.getId();
            case 1: return cliente.getNome();
            case 2: return cliente.getLogradouro();
            case 3: return cliente.getNumero();
            case 4: return cliente instanceof Fisica ? ((Fisica) cliente).getCpf() : ((Juridica) cliente).getCnpj();
            case 5: return cliente.getTelefone();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Cliente getClienteAt(int index) {
        return clientes.get(index);
    }

    public void setClientes(List<Cliente> novosClientes) {
        this.clientes = novosClientes != null ? novosClientes : new ArrayList<>();
        fireTableDataChanged(); // Notifica a tabela que os dados foram alterados
    }
}
