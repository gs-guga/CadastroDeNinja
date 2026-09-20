package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @Autowired
    private MissoesService missoesService;

    //criar
    @PostMapping("/criar")
    public MissoesDTO criarMissoes(@RequestBody MissoesDTO criarMissoesModel) {
        return missoesService.criarMissoes(criarMissoesModel);
    }

    //listar
    @GetMapping("/listar")
    public List<MissoesDTO> listarMissoes() {
        return missoesService.listarMissoes();
    }

    //ListarID
    @GetMapping("/listar/{id}")
    public MissoesDTO listarMissoesId(@PathVariable Long id) {
        return missoesService.listarPorId( id);
    }

    //alterar
    @PutMapping("/alterar/{id}")
    public MissoesDTO alterarMissoes(@PathVariable Long id, @RequestBody MissoesDTO missoesDtoAtualizado) {
        return missoesService.alterarMissoes(id, missoesDtoAtualizado);
    }

    //deletar
    @DeleteMapping("/deletar/{id}")
    public void deletarMissoes(@PathVariable Long id) {
        missoesService.deletarMissoes(id);
    }
}
