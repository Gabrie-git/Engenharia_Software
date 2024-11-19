package Atendimento.Controller;

import Atendimento.DAO.AnimalDAO;
import Atendimento.Model.Animal;
import java.util.List;

public class GerenciaAnimal {

    private AnimalDAO animalDAO;

    public GerenciaAnimal() {
        this.animalDAO = new AnimalDAO();
    }

    public List<Animal> getAllAnimal() {
        return animalDAO.listarTodosAnimais();
    }

    public boolean addAnimal(Animal animal) {
        animalDAO.adicionarAnimal(animal);
        return true;
    }

    public Animal getAnimalById(int id) {
        return animalDAO.buscarAnimalPorId(id);
    }

    public boolean updateAnimal(int id, Animal novoAnimal) {
        Animal animal = getAnimalById(id);
        if (animal != null) {
            animal.setNome(novoAnimal.getNome());
            animal.setDataNascimento(novoAnimal.getDataNascimento());
            animal.setRaca(novoAnimal.getRaca());
            animal.setPeso(novoAnimal.getPeso());
            animal.setAltura(novoAnimal.getAltura());
            animal.setDono(novoAnimal.getDono());
            animal.setImagem(novoAnimal.getImagem());
            
            animalDAO.atualizarAnimal(animal);
            return true;
        }
        return false;
    }

    public boolean deleteAnimal(int id) {
        animalDAO.removerAnimal(id);
        return true;
    }
}
