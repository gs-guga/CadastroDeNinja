package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NinjasService {

    private NinjaRepository ninjaRepository;

    public NinjasService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;

    }

    public List<NinjaModel> listarNinja (){
        return ninjaRepository.findAll();
    }

}
