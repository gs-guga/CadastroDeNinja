package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;
    private MissoesMapper missoesMapper;

    public MissoesService(MissoesRepository missoesRepository, MissoesMapper missoesMapper) {
        this.missoesRepository = missoesRepository;
        this.missoesMapper = missoesMapper;
    }

    // Listar todas as missões
    public List<MissoesDTO> listarMissoes() {
        List<MissoesModel> missoes = missoesRepository.findAll();

        return missoes.stream()
                .map(missoesMapper::map)
                .collect(Collectors.toList());
    }

    // Listar missão por ID
    public MissoesDTO listarPorId(Long id) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        return missoesPorId.map(missoesMapper::map).orElse(null);
    }

    // Criar uma missão na tabela
    public MissoesDTO criarMissoes(MissoesDTO missoesDTO) {
        MissoesModel missao = missoesMapper.map(missoesDTO);
        missao = missoesRepository.save(missao);
        return missoesMapper.map(missao);
    }

    // Alterar uma missão na tabela
    public MissoesDTO alterarMissoes(Long id, MissoesDTO missoesDTO) {
        Optional<MissoesModel> missoesPorId = missoesRepository.findById(id);
        if (missoesPorId.isPresent()) {
            MissoesModel missaoAtualizada = missoesMapper.map(missoesDTO);
            missaoAtualizada.setId(id);
            MissoesModel missao = missoesRepository.save(missaoAtualizada);
            return missoesMapper.map(missao);
        }
        return null;
    }

    // Deletar uma missão na tabela
    public void deletarMissoes(Long id) {
        missoesRepository.deleteById(id);
    }
}
