package br.com.trabalhowebsite.trabalho_website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.trabalhowebsite.trabalho_website.model.Professor;
import br.com.trabalhowebsite.trabalho_website.repository.ProfessorRepository;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        professorRepository.deleteById(id);
    }
}