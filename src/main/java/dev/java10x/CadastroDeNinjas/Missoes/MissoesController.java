package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {

    @PostMapping("/criar")
    public String criarMissoes() {
        return "Criando Missoes";
    }

    @GetMapping("/listar")
    public String listarMissoes() {
        return "Listando Missoes";
    }

    @PutMapping("/alterar")
    public String alterarMissoes() {
        return "Alterando Missoes";
    }

    @DeleteMapping("/deletar")
    public String deletarMissoes() {
        return "Deletando Missoes";
    }
}
