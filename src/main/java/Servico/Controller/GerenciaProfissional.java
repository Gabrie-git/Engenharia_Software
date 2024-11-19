package Servico.Controller;

import Servico.DAO.ProfissionalDAO;
import Servico.Model.Profissional;
import java.util.List;

public class GerenciaProfissional {

    private ProfissionalDAO profissionalDAO;

    public GerenciaProfissional() {
        this.profissionalDAO = new ProfissionalDAO();
    }

    public void cadastrarProfissional(Profissional profissional) {
        profissionalDAO.adicionarProfissional(profissional);
    }

    public List<Profissional> getAllProfissional() {
        return profissionalDAO.listarTodosProfissionais();
    }

    public Profissional getProfissionalById(int id) {
        return profissionalDAO.buscarProfissionalPorId(id);
    }

    public void alterarProfissional(int id, Profissional novoProfissional) {
        Profissional profissionalExistente = profissionalDAO.buscarProfissionalPorId(id);
        if (profissionalExistente != null) {
            profissionalExistente.setNome(novoProfissional.getNome());
            profissionalExistente.setEspecialidade(novoProfissional.getEspecialidade());
            profissionalDAO.atualizarProfissional(profissionalExistente);
        }
    }

    public void removerProfissional(int id) {
        profissionalDAO.removerProfissional(id);
    }

    public List<Profissional> listarProfissionais() {
        return profissionalDAO.listarTodosProfissionais();
    }
}
