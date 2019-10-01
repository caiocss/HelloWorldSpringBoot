package com.example.demoapiproject.controller;

import com.example.demoapiproject.controller.dto.DetalhesTopicoDto;
import com.example.demoapiproject.controller.dto.TopicoDto;
import com.example.demoapiproject.controller.form.AtualizacaoTopicoForm;
import com.example.demoapiproject.controller.form.TopicoForm;
import com.example.demoapiproject.modelo.Topico;
import com.example.demoapiproject.repository.CursoRepository;
import com.example.demoapiproject.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    // A annotation @Valid faz disparar as validações do Bean validation e devolve um erro 400
    // Caso a informação enviada pelo Cliente esteja incorreta
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriBuilder) {
        Topico topico = topicoForm.convert(cursoRepository);
        topicosRepository.save(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable long id) {
        // O método findById retorna um objeto Optional<>, que pode ou não conter um objeto, ou contrario do ->
        // O método getOne que lança uma exception quando o id passado como parâmetro não existir no banco de dados
        Optional<Topico> topico = topicosRepository.findById(id);

        if(topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    // Métodos anotados com @Transactional serão executados dentro de um contexto transacional.
    // Ao finalizar o método, o Spring efetuará o commit automático da transação no banco de dados,
    // caso nenhuma exception tenha sido lançada..
    @Transactional
    public ResponseEntity<TopicoDto> atualizar(@PathVariable long id, @RequestBody @Valid AtualizacaoTopicoForm atualizacaoTopicoForm) {
        Optional<Topico> topicoOptional = topicosRepository.findById(id);

        if(topicoOptional.isPresent()) {
            Topico topico = atualizacaoTopicoForm.atualizar(id, topicosRepository);
            return ResponseEntity.ok(new TopicoDto(topico));
        }
        return ResponseEntity.notFound().build();
    }

    // Para tratar o erro 404 na classe controller, devemos utilizar o método findById, ao invés do método getOne,
    // e utilizar a classe ResponseEntity para montar a resposta de not found
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity remover(@PathVariable long id){
        Optional<Topico> topicoOptional = topicosRepository.findById(id);
        if(topicoOptional.isPresent()) {
            topicosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
