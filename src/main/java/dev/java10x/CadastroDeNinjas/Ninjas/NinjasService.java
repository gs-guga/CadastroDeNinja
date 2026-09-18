package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjasService {

    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjasService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    //listar todos meus ninjas
    public List<NinjaDTO> listarNinja() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();

        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    //Listar por id
    public NinjaDTO  listarID(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    //criar um ninja na tabela
    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel ninja = ninjaMapper.map(ninjaDTO);
        ninja = ninjaRepository.save(ninja);
        return ninjaMapper.map(ninja);
    }

    //altera um ninja na tabela
    public NinjaDTO  atualizarNinja(Long id ,NinjaDTO  ninjaDTO) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        if (ninjaPorId.isPresent()) {
            NinjaModel ninjaAtualizado = ninjaMapper.map(ninjaDTO);
            ninjaAtualizado.setId(id);
            NinjaModel ninja = ninjaRepository.save(ninjaAtualizado);
            return ninjaMapper.map(ninja);
        }return  null;
    }


    //deleta um ninja na tabela
    public void deletarNinja(Long id) {
        ninjaRepository.deleteById(id);
    }
}

