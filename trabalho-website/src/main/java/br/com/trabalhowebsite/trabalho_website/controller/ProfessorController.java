package br.com.trabalhowebsite.trabalho_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.trabalhowebsite.trabalho_website.model.Professor;
import br.com.trabalhowebsite.trabalho_website.service.ProfessorService;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("professores", professorService.listarTodos());

        return "professores/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("professor", new Professor());

        return "professores/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Professor professor) {
        professorService.salvar(professor);

        return "redirect:/professores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Professor professor = professorService.buscarPorId(id);

        if (professor == null) {
            return "redirect:/professores";
        }

        model.addAttribute("professor", professor);

        return "professores/formulario";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        professorService.excluir(id);

        return "redirect:/professores";
    }
}