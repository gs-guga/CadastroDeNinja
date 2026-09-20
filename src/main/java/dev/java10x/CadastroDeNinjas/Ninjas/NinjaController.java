package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjasService ninjasService;

    public NinjaController(NinjasService ninjasService) {
        this.ninjasService = ninjasService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Boas vindas";
    }

    // adicionar ninja (CREATE)
    @PostMapping("/criar")
    public NinjaDTO adicionarNinja(@RequestBody NinjaDTO ninjaDTO) {
        return ninjasService.criarNinja(ninjaDTO);
    }

    //mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaDTO > todos() {
        return ninjasService.listarNinja();
    }

    //mostrar ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public NinjaDTO todosID(@PathVariable Long id) {
        return ninjasService.listarID(id);
    }

    //alterar dados do ninja (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjaDTO alterarID(@PathVariable Long id, @RequestBody NinjaDTO ninjaModelAtualizado) {
        return ninjasService.atualizarNinja(id, ninjaModelAtualizado);
    }

    //deletar ninjas(DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deleteID(@PathVariable Long id) {
        ninjasService.deletarNinja(id);
    }
}
