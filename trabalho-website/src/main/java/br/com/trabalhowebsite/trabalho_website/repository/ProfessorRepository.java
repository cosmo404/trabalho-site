package br.com.trabalhowebsite.trabalho_website.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.trabalhowebsite.trabalho_website.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}