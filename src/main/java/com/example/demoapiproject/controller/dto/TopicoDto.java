package com.example.demoapiproject.controller.dto;

import com.example.demoapiproject.modelo.Topico;
import com.sun.istack.internal.Nullable;

import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDto {
    private long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;


    public TopicoDto(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }

    public static List<TopicoDto> convert(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
