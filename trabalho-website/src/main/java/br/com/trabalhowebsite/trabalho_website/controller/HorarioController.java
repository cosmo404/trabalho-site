package br.com.trabalhowebsite.trabalho_website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.trabalhowebsite.trabalho_website.model.Disciplina;
import br.com.trabalhowebsite.trabalho_website.model.Horario;
import br.com.trabalhowebsite.trabalho_website.model.Professor;
import br.com.trabalhowebsite.trabalho_website.model.Turma;
import br.com.trabalhowebsite.trabalho_website.service.DisciplinaService;
import br.com.trabalhowebsite.trabalho_website.service.HorarioService;
import br.com.trabalhowebsite.trabalho_website.service.ProfessorService;
import br.com.trabalhowebsite.trabalho_website.service.TurmaService;

@Controller
@RequestMapping("/horarios")
public class HorarioController {

    private final HorarioService horarioService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private final TurmaService turmaService;

    public HorarioController(
            HorarioService horarioService,
            ProfessorService professorService,
            DisciplinaService disciplinaService,
            TurmaService turmaService) {

        this.horarioService = horarioService;
        this.professorService = professorService;
        this.disciplinaService = disciplinaService;
        this.turmaService = turmaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("horarios", horarioService.listarTodos());
        return "horarios/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {

        model.addAttribute("horario", new Horario());
        carregarOpcoes(model);

        return "horarios/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Horario horario,
            @RequestParam Long professorId,
            @RequestParam Long disciplinaId,
            @RequestParam Long turmaId) {

        Professor professor = professorService.buscarPorId(professorId);
        Disciplina disciplina = disciplinaService.buscarPorId(disciplinaId);
        Turma turma = turmaService.buscarPorId(turmaId);

        if (professor == null || disciplina == null || turma == null) {
            return "redirect:/horarios";
        }

        horario.setProfessor(professor);
        horario.setDisciplina(disciplina);
        horario.setTurma(turma);

        horarioService.salvar(horario);

        return "redirect:/horarios";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Horario horario = horarioService.buscarPorId(id);

        if (horario == null) {
            return "redirect:/horarios";
        }

        model.addAttribute("horario", horario);
        carregarOpcoes(model);

        return "horarios/formulario";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        horarioService.excluir(id);

        return "redirect:/horarios";
    }

    private void carregarOpcoes(Model model) {

        model.addAttribute("professores", professorService.listarTodos());
        model.addAttribute("disciplinas", disciplinaService.listarTodos());
        model.addAttribute("turmas", turmaService.listarTodos());
    }
}