package br.com.trabalhowebsite.trabalho_website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.trabalhowebsite.trabalho_website.model.Disciplina;
import br.com.trabalhowebsite.trabalho_website.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public List<Disciplina> listarTodos() {
        return disciplinaRepository.findAll();
    }

    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina buscarPorId(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        disciplinaRepository.deleteById(id);
    }
}