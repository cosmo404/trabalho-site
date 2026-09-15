package br.com.trabalhowebsite.trabalho_website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.trabalhowebsite.trabalho_website.model.Turma;
import br.com.trabalhowebsite.trabalho_website.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> listarTodos() {
        return turmaRepository.findAll();
    }

    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        turmaRepository.deleteById(id);
    }
}