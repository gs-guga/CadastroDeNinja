package dev.java10x.CadastroDeNinjas.Ninjas;

import dev.java10x.CadastroDeNinjas.Missoes.MissoesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinjaControllerUi {

    private final NinjasService ninjasService;

    private final MissoesService missoesService;

    public NinjaControllerUi(NinjasService ninjasService, MissoesService missoesService) {
        this.ninjasService = ninjasService;
        this.missoesService = missoesService;
    }

    // Listar todos os ninjas na tela (READ)
    @GetMapping("/listar")
    public String listarNinjas(Model model) {
        List<NinjaDTO> ninjas = ninjasService.listarNinja();
        model.addAttribute("ninjas", ninjas);
        return "listarNinjas";
    }

    // Detalhar um ninja específico por ID (READ)
    @GetMapping("/listar/{id}")
    public String detalharNinja(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjasService.listarID(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            return "detalharNinja";
        } else {
            model.addAttribute("mensagemErro", "Ninja não encontrado!");
            return "erro";
        }
    }

    // Abrir o formulário de cadastro de Ninja (CREATE - GET)
    @GetMapping("/adicionar")
    public String mostrarFormularioAdicionar(Model model) {
        model.addAttribute("ninja", new NinjaDTO());
        model.addAttribute("missoes", missoesService.listarMissoes()); // Envia a lista de missões para a tela
        return "adicionarNinja";
    }

    // Processar o envio do formulário de cadastro (CREATE - POST)
    @PostMapping("/salvar")
    public String salvarNinja(@ModelAttribute("ninja") NinjaDTO ninjaDTO) {
        ninjasService.criarNinja(ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    // Abrir o formulário de edição de Ninja (UPDATE - GET)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        NinjaDTO ninja = ninjasService.listarID(id);
        if (ninja != null) {
            model.addAttribute("ninja", ninja);
            model.addAttribute("missoes", missoesService.listarMissoes()); // Envia a lista de missões para a tela
            return "editarNinja";
        } else {
            model.addAttribute("mensagemErro", "Ninja não encontrado para edição!");
            return "erro";
        }
    }

    // Processar a atualização do Ninja (UPDATE - POST)
    @PostMapping("/atualizar/{id}")
    public String atualizarNinja(@PathVariable Long id, @ModelAttribute("ninja") NinjaDTO ninjaDTO) {
        ninjasService.atualizarNinja(id, ninjaDTO);
        return "redirect:/ninjas/ui/listar";
    }

    // Deletar Ninja através da UI (DELETE)
    @GetMapping("/deletar/{id}")
    public String deletarNinja(@PathVariable Long id) {
        if (ninjasService.listarID(id) != null) {
            ninjasService.deletarNinja(id);
        }
        return "redirect:/ninjas/ui/listar";
    }
}