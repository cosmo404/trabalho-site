package br.com.trabalhowebsite.trabalho_website.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.trabalhowebsite.trabalho_website.model.Horario;
import br.com.trabalhowebsite.trabalho_website.repository.HorarioRepository;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    public Horario salvar(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario buscarPorId(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        horarioRepository.deleteById(id);
    }

    public List<Horario> buscarPorProfessor(Long professorId) {
        return horarioRepository.findByProfessorId(professorId);
    }

    public List<Horario> buscarPorTurma(Long turmaId) {
        return horarioRepository.findByTurmaId(turmaId);
    }
}