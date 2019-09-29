package com.example.demoapiproject.controller;

import com.example.demoapiproject.controller.dto.TopicoDto;
import com.example.demoapiproject.controller.form.TopicoForm;
import com.example.demoapiproject.modelo.Topico;
import com.example.demoapiproject.repository.CursoRepository;
import com.example.demoapiproject.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
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

    @PostMapping
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.convert(cursoRepository);
        topicosRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }
}
