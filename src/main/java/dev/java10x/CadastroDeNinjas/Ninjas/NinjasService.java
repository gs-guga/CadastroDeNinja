package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NinjasService {

    private NinjaRepository ninjaRepository;

    public NinjasService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;

    }

    //listar todos meus ninjas
    public List<NinjaModel> listarNinja() {
        return ninjaRepository.findAll();
    }

    public NinjaModel listarID(Long id) {
        Optional<NinjaModel> ninjaModel = ninjaRepository.findById(id);
        return ninjaModel.orElse(null);
    }

    //criar um ninja na tabela
    public NinjaModel criarNinja(NinjaModel ninjaModel) {
        return ninjaRepository.save(ninjaModel);
    }

    //altera um ninja na tabela
    //deleta um ninja na tabela
}

