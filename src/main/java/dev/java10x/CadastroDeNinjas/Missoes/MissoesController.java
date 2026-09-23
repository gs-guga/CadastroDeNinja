package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    private final MissoesService missoesService;


    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // criar missão (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissoes(@RequestBody MissoesDTO criarMissoesModel) {
        MissoesDTO novaMissao = missoesService.criarMissoes(criarMissoesModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão adicionada com sucesso!");
    }

    // mostrar todas as missões (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // mostrar missões por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissoesId(@PathVariable Long id) {
        MissoesDTO missao = missoesService.listarPorId(id);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada!");
        }
    }

    // alterar dados da missão (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissoes(@PathVariable Long id, @RequestBody MissoesDTO missoesDtoAtualizado) {
        MissoesDTO missao = missoesService.alterarMissoes(id, missoesDtoAtualizado);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada!");
        }
    }

    // deletar missões (DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissoes(@PathVariable Long id) {
        if (missoesService.listarPorId(id) != null) {
            missoesService.deletarMissoes(id);
            return ResponseEntity.ok("Missão com o id " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada!");
        }
    }
}
