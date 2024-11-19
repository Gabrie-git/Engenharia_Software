
package Financeiro.Controller;

import Financeiro.DAO.ItemDAO;
import Financeiro.Model.Item;
import Financeiro.Model.Produto;
import java.util.List;

public class GerenciaProduto {
    private ItemDAO itemDAO;

    public GerenciaProduto() {
        this.itemDAO = new ItemDAO();
    }

    public boolean addProduto(Produto produto) {
        itemDAO.adicionarItem(produto);
        return true;
    }

    public Produto buscarProdutoPorId(int id) {
        Item item = itemDAO.getItemById(id);
        return (item instanceof Produto) ? (Produto) item : null;
    }

    public List<Item> getAllProdutos() {
        // Filtra todos os produtos a partir da lista geral de itens
        return itemDAO.getAllServicos(); // Mude para um método específico para produtos se necessário
    }

    public boolean updateProduto(int id, Produto novoProduto) {
        Item itemExistente = itemDAO.getItemById(id);
        if (itemExistente instanceof Produto) {
            Produto produtoExistente = (Produto) itemExistente;
            produtoExistente.setDescricao(novoProduto.getDescricao());
            produtoExistente.setPreco(novoProduto.getPreco());
            produtoExistente.setValidade(novoProduto.getValidade());
            produtoExistente.setMarca(novoProduto.getMarca());
            produtoExistente.setModelo(novoProduto.getModelo());
            produtoExistente.setQuantidade(novoProduto.getQuantidade());
            itemDAO.atualizarItem(produtoExistente);
            return true;
        }
        return false;
    }

    public boolean deleteProduto(int id) {
        itemDAO.deletarItem(id);
        return true;
    }
}

