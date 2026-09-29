package dev.java10x.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjasService ninjasService;

    public NinjaController(NinjasService ninjasService) {
        this.ninjasService = ninjasService;
    }

    // boas vindas
    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota dá boas vindas para quem acessa ela")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boas vindas exibidas com sucesso")
    })
    public String boasVindas() {
        return "Boas vindas";
    }

    // adicionar ninja (CREATE)
    @PostMapping("/criar")
    @Operation(summary = "Cria um ninja", description = "Rota que cria um novo ninja na tabela ninjas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja")
    })
    public ResponseEntity<String> adicionarNinja(
            @Parameter(description = "Dados do novo ninja a ser cadastrado")
            @RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO novoNinja = ninjasService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja adicionado com sucesso!");
    }

    // mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Rota que retorna todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de ninjas retornada com sucesso")
    })
    public ResponseEntity<List<NinjaDTO>> todos() {
        List<NinjaDTO> ninjas = ninjasService.listarNinja();
        return ResponseEntity.ok(ninjas);
    }

    // mostrar ninjas por id (READ)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Busca ninja por ID", description = "Rota que busca e retorna as informações de um ninja pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> todosID(
            @Parameter(description = "ID do ninja que deseja buscar", example = "1")
            @PathVariable Long id) {
        NinjaDTO ninja = ninjasService.listarID(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado!");
        }
    }

    // alterar dados do ninja (UPDATE)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera um ninja por ID", description = "Rota que atualiza os dados de um ninja existente pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado para atualização")
    })
    public ResponseEntity<?> alterarID(
            @Parameter(description = "ID do ninja que deseja alterar", example = "1")
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do ninja")
            @RequestBody NinjaDTO ninjaModelAtualizado) {
        NinjaDTO ninja = ninjasService.atualizarNinja(id, ninjaModelAtualizado);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado!");
        }
    }

    // deletar ninjas (DELETE)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta um ninja por ID", description = "Rota que remove um ninja da tabela pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado para deleção")
    })
    public ResponseEntity<String> deleteID(
            @Parameter(description = "ID do ninja que deseja deletar", example = "1")
            @PathVariable Long id) {
        if (ninjasService.listarID(id) != null) {
            ninjasService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado");
        }
    }
}