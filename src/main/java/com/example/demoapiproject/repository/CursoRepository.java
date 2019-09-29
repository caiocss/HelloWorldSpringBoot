package com.example.demoapiproject.repository;

import com.example.demoapiproject.modelo.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNome(String nomeDoCurso);
}
