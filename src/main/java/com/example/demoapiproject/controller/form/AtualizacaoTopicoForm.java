package com.example.demoapiproject.controller.form;

import com.example.demoapiproject.modelo.Topico;
import com.example.demoapiproject.repository.TopicosRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 10)
    private String mensagem;

    public Topico atualizar(long id, TopicosRepository topicosRepository) {
        Topico topico = topicosRepository.getOne(id);
        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);
        return topico;
    }
}
