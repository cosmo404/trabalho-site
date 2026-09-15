package br.com.trabalhowebsite.trabalho_website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trabalhowebsite.trabalho_website.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByProfessorId(Long professorId);

    List<Horario> findByTurmaId(Long turmaId);
}