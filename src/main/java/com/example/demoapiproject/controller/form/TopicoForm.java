package com.example.demoapiproject.controller.form;

import com.example.demoapiproject.modelo.Curso;
import com.example.demoapiproject.modelo.Topico;
import com.example.demoapiproject.repository.CursoRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class TopicoForm {
    @NotNull @NotEmpty @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeDoCurso;

    public Topico convert(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(this.nomeDoCurso);
        return new Topico(this.titulo, this.mensagem, curso);
    }
}
