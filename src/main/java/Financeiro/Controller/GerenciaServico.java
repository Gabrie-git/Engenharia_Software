
package Financeiro.Controller;

import Financeiro.DAO.ItemDAO;
import Financeiro.Model.Item;
import Financeiro.Model.Servico;
import java.util.List;

public class GerenciaServico {
    private ItemDAO itemDAO;

    public GerenciaServico() {
        this.itemDAO = new ItemDAO();
    }

    public boolean addServico(Servico servico) {
        itemDAO.adicionarItem(servico);
        return true;
    }

    public Servico buscarServicoPorId(int id) {
        Item item = itemDAO.getItemById(id);
        return (item instanceof Servico) ? (Servico) item : null;
    }

    public List<Item> getAllServicos() {
        return itemDAO.getAllServicos();
    }

    public boolean updateServico(int id, Servico novoServico) {
        Item itemExistente = itemDAO.getItemById(id);
        if (itemExistente instanceof Servico) {
            Servico servicoExistente = (Servico) itemExistente;
            servicoExistente.setDescricao(novoServico.getDescricao());
            servicoExistente.setPreco(novoServico.getPreco());
            servicoExistente.setProfissional(novoServico.getProfissional());
            servicoExistente.setEquipamentos(novoServico.getEquipamentos());
            itemDAO.atualizarItem(servicoExistente);
            return true;
        }
        return false;
    }

    public boolean deleteServico(int id) {
        itemDAO.deletarItem(id);
        return true;
    }
}

