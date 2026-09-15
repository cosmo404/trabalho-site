package br.com.trabalhowebsite.trabalho_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.trabalhowebsite.trabalho_website.model.Professor;
import br.com.trabalhowebsite.trabalho_website.model.Turma;
import br.com.trabalhowebsite.trabalho_website.service.HorarioService;
import br.com.trabalhowebsite.trabalho_website.service.ProfessorService;
import br.com.trabalhowebsite.trabalho_website.service.TurmaService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    private final ProfessorService professorService;
    private final TurmaService turmaService;
    private final HorarioService horarioService;

    public ConsultaController(
            ProfessorService professorService,
            TurmaService turmaService,
            HorarioService horarioService) {

        this.professorService = professorService;
        this.turmaService = turmaService;
        this.horarioService = horarioService;
    }

    @GetMapping("/professor")
    public String porProfessor(
            @RequestParam(required = false) Long professorId,
            Model model) {

        model.addAttribute("professores", professorService.listarTodos());

        if (professorId != null) {

            Professor professor = professorService.buscarPorId(professorId);

            model.addAttribute("professorSelecionado", professor);

            if (professor != null) {
                model.addAttribute(
                        "horarios",
                        horarioService.buscarPorProfessor(professorId)
                );
            }
        }

        return "consultas/professor";
    }

    @GetMapping("/turma")
    public String porTurma(
            @RequestParam(required = false) Long turmaId,
            Model model) {

        model.addAttribute("turmas", turmaService.listarTodos());

        if (turmaId != null) {

            Turma turma = turmaService.buscarPorId(turmaId);

            model.addAttribute("turmaSelecionada", turma);

            if (turma != null) {
                model.addAttribute(
                        "horarios",
                        horarioService.buscarPorTurma(turmaId)
                );
            }
        }

        return "consultas/turma";
    }
}
