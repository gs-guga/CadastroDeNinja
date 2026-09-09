package dev.java10x.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {

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
    @GetMapping("/todos")
    public String todos() {
        return "Listando todos os ninjas ";
    }

    //mostrar ninjas por id (READ)
    @GetMapping("/todosID")
    public String todosID() {
        return "Listando todos por Id";
    }

    //alterar dados do ninja (UPDATE)
    @PutMapping("/alterarID")
    public String alterarID() {
        return "Alterando ninja por Id";
    }

    //deletar ninjas(DELETE)
    @DeleteMapping("/deleteID")
    public String deleteID() {
        return "Deletando ninja por Id";
    }
}
