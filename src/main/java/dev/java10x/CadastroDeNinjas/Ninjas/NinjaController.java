package dev.java10x.CadastroDeNinjas.Ninjas;

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

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Boas vindas";
    }

    // adicionar ninja (CREATE)
    @PostMapping("/criar")
    public ResponseEntity<String> adicionarNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO novoNinja = ninjasService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja adicionada com sucesso!");
    }

    //mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public ResponseEntity<List<NinjaDTO>> todos()
    {
        List <NinjaDTO> ninjas = ninjasService.listarNinja();
        return ResponseEntity.ok(ninjas);
    }

    //mostrar ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> todosID(@PathVariable Long id) {
       NinjaDTO ninja = ninjasService.listarID(id);

       if (ninja != null) {
           return ResponseEntity.ok(ninja);
       }else  {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Ninja nao encontrada!");
       }
    }

    //alterar dados do ninja (UPDATE)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarID(@PathVariable Long id, @RequestBody NinjaDTO ninjaModelAtualizado) {
        NinjaDTO ninja = ninjasService.atualizarNinja(id, ninjaModelAtualizado);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja nao encontrado!");
        }
    }

    //deletar ninjas(DELETE)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteID(@PathVariable Long id)
    {
        if (ninjasService.listarID(id) != null){
            ninjasService.deletarNinja(id);
            return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso!");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado");
        }
    }
}
