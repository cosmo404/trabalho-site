package br.com.trabalhowebsite.trabalho_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.trabalhowebsite.trabalho_website.model.Disciplina;
import br.com.trabalhowebsite.trabalho_website.service.DisciplinaService;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disciplinas", disciplinaService.listarTodos());
        return "disciplinas/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("disciplina", new Disciplina());
        return "disciplinas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina disciplina) {
        disciplinaService.salvar(disciplina);
        return "redirect:/disciplinas";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Disciplina disciplina = disciplinaService.buscarPorId(id);

        if (disciplina == null) {
            return "redirect:/disciplinas";
        }

        model.addAttribute("disciplina", disciplina);

        return "disciplinas/formulario";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        disciplinaService.excluir(id);
        return "redirect:/disciplinas";
    }
}