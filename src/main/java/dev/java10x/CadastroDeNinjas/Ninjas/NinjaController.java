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
    public String adicionarNinja() {
        return "Adicionando Ninja";
    }

    //mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> todos() {
        return ninjasService.listarNinja();
    }

    //mostrar ninjas por id (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel todosID(@PathVariable Long id) {
        return ninjasService.listarID(id);
    }

    //alterar dados do ninja (UPDATE)
    @PutMapping("/alterar")
    public String alterarID() {
        return "Alterando ninja por Id";
    }

    //deletar ninjas(DELETE)
    @DeleteMapping("/deletar")
    public String deleteID() {
        return "Deletando ninja por Id";
    }
}
