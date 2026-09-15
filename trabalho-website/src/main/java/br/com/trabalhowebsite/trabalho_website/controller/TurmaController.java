package br.com.trabalhowebsite.trabalho_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.trabalhowebsite.trabalho_website.model.Turma;
import br.com.trabalhowebsite.trabalho_website.service.TurmaService;

@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("turmas", turmaService.listarTodos());
        return "turmas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("turma", new Turma());
        return "turmas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Turma turma) {
        turmaService.salvar(turma);
        return "redirect:/turmas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Turma turma = turmaService.buscarPorId(id);

        if (turma == null) {
            return "redirect:/turmas";
        }

        model.addAttribute("turma", turma);

        return "turmas/formulario";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        turmaService.excluir(id);
        return "redirect:/turmas";
    }
}
