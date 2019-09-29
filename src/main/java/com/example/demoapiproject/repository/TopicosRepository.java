package com.example.demoapiproject.repository;

import com.example.demoapiproject.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicosRepository extends JpaRepository<Topico, Long> {

    //Busca utilizando o nome da Classe
    List<Topico> findByCursoNome(String nomeCurso);

    //Busca personalizada

}
