package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/missoes/ui")
public class MissoesControllerUi {

    private final MissoesService missoesService;

    public MissoesControllerUi(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // Listar todas as missões na tela (READ)
    @GetMapping("/listar")
    public String listarMissoes(Model model) {
        List<MissoesDTO> missoes = missoesService.listarMissoes();
        model.addAttribute("missoes", missoes);
        return "listarMissoes";
    }

    // Detalhar uma missão por ID (READ)
    @GetMapping("/listar/{id}")
    public String detalharMissao(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarPorId(id);
        if (missao != null) {
            model.addAttribute("missao", missao);
            return "detalharMissao";
        } else {
            model.addAttribute("mensagemErro", "Missão não encontrada!");
            return "erro";
        }
    }

    // Abrir formulário de cadastro de Missão (CREATE - GET)
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("missao", new MissoesDTO());
        return "adicionarMissao";
    }

    // Processar o envio do formulário de cadastro (CREATE - POST)
    @PostMapping("/salvar")
    public String salvarMissao(@ModelAttribute("missao") MissoesDTO missoesDTO) {
        missoesService.criarMissoes(missoesDTO);
        return "redirect:/missoes/ui/listar";
    }

    // Abrir formulário de edição de Missão (UPDATE - GET)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        MissoesDTO missao = missoesService.listarPorId(id);
        if (missao != null) {
            model.addAttribute("missao", missao);
            return "editarMissao";
        } else {
            model.addAttribute("mensagemErro", "Missão não encontrada para edição!");
            return "erro";
        }
    }

    // Processar a atualização da Missão (UPDATE - POST)
    @PostMapping("/atualizar/{id}")
    public String atualizarMissao(@PathVariable Long id, @ModelAttribute("missao") MissoesDTO missoesDTO) {
        missoesService.alterarMissoes(id, missoesDTO);
        return "redirect:/missoes/ui/listar";
    }

    // Deletar Missão através da UI (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletarMissao(@PathVariable Long id) {
        if (missoesService.listarPorId(id) != null) {
            missoesService.deletarMissoes(id);
        }
        return "redirect:/missoes/ui/listar";
    }
}