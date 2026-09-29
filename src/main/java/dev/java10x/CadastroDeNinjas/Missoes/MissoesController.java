package dev.java10x.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Cria uma missão", description = "Rota que cria uma nova missão na tabela missoes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão")
    })
    public ResponseEntity<String> criarMissoes(
            @Parameter(description = "Dados da nova missão a ser cadastrada")
            @RequestBody MissoesDTO criarMissoesModel) {
        MissoesDTO novaMissao = missoesService.criarMissoes(criarMissoesModel);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão adicionada com sucesso!");
    }

    // mostrar todas as missões (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões", description = "Rota que retorna todas as missões cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de missões retornada com sucesso")
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    // mostrar missões por id (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Busca missão por ID", description = "Rota que busca e retorna as informações de uma missão pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<?> listarMissoesId(
            @Parameter(description = "ID da missão que deseja buscar", example = "1")
            @PathVariable Long id) {
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
    @Operation(summary = "Altera uma missão por ID", description = "Rota que atualiza os dados de uma missão existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada para atualização")
    })
    public ResponseEntity<?> alterarMissoes(
            @Parameter(description = "ID da missão que deseja alterar", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados da missão")
            @RequestBody MissoesDTO missoesDtoAtualizado) {
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
    @Operation(summary = "Deleta uma missão por ID", description = "Rota que remove uma missão da tabela pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada para deleção")
    })
    public ResponseEntity<String> deletarMissoes(
            @Parameter(description = "ID da missão que deseja deletar", example = "1")
            @PathVariable Long id) {
        if (missoesService.listarPorId(id) != null) {
            missoesService.deletarMissoes(id);
            return ResponseEntity.ok("Missão com o id " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada!");
        }
    }
}
