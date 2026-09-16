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
    public MissoesModel criarMissoes(@RequestBody MissoesModel criarMissoesModel) {
        return missoesService.criarMissoes(criarMissoesModel);
    }

    //listar
    @GetMapping("/listar")
    public List<MissoesModel> listarMissoes() {
        return missoesService.listarMissoes();
    }

    //ListarID
    @GetMapping("/listar/{id}")
    public MissoesModel listarMissoesId(@PathVariable Long id) {
        return missoesService.listarPorId( id);
    }

    //alterar
    @PutMapping("/alterar/{id}")
    public MissoesModel alterarMissoes(@PathVariable Long id, @RequestBody MissoesModel missoesModelAtualizado) {
        return missoesService.alterarMissoes(id, missoesModelAtualizado);
    }

    /* */

    //deletar
    @DeleteMapping("/deletar/{id}")
    public void deletarMissoes(@PathVariable Long id) {
        missoesService.deletarMissoes(id);
    }
}
