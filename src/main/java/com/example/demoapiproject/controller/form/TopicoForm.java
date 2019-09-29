package com.example.demoapiproject.controller.form;

import com.example.demoapiproject.modelo.Curso;
import com.example.demoapiproject.modelo.Topico;
import com.example.demoapiproject.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;

public class TopicoForm {
    @Getter @Setter
    private String titulo;
    @Getter @Setter
    private String mensagem;
    @Getter @Setter
    private String nomeDoCurso;

    public Topico convert(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(this.nomeDoCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }
}
