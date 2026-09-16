package dev.java10x.CadastroDeNinjas.Missoes;

import dev.java10x.CadastroDeNinjas.Ninjas.NinjaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    @Autowired
    private MissoesRepository missoesRepository;

    //criar
    public MissoesModel criarMissoes(MissoesModel criarMissoesModel) {
        return missoesRepository.save(criarMissoesModel);
    }

    //Listar
    public List<MissoesModel> listarMissoes() {
        return missoesRepository.findAll();
    }

    //Listar por id
    public MissoesModel listarPorId(Long id) {
      Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
      return missoesModel.orElse(null);
    }

    //alterar
    public MissoesModel alterarMissoes(Long id ,MissoesModel missoesAlterada) {
        if (missoesRepository.existsById(id)) {
            missoesAlterada.setId(id);
            return missoesRepository.save(missoesAlterada);
        }return  null;
    }

    public void deletarMissoes(Long id) {
        missoesRepository.deleteById(id);
    }

}
