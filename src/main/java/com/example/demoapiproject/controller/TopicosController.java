package com.example.demoapiproject.controller;

import com.example.demoapiproject.controller.dto.TopicoDto;
import com.example.demoapiproject.modelo.Curso;
import com.example.demoapiproject.modelo.Topico;
import com.example.demoapiproject.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso) {

        if (nomeCurso == null) {
            List<Topico> topicos = topicosRepository.findAll();
            return TopicoDto.convert(topicos);
        }
        else {
            List<Topico> topicos = topicosRepository.findByCursoNome(nomeCurso);
            return TopicoDto.convert(topicos);
        }


    }
}
