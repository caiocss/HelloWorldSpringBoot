package com.example.demoapiproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

// Controller para interceptar as exceptions lançadas nos métodos de todas as classes controller
// e chama o método informado de acordo com o erro.
@RestControllerAdvice
public class ErroDeValidacaoHandler {

    // Spring ajuda na questão de internacionalização com a classe MessageSource.
    // Essa classe ajuda a pegar a mensagem de erro de acordo com o indioma que o dev informar.
    @Autowired
    private MessageSource messageSource;

    // Para tratar os erros de validação do Bean Validation e personalizar o JSON, que será
    // devolvido ao cliente da API, com as mensagens de erro, devemos criar um método na classe
    // @RestControllerAdvice e anotá-lo com @ExceptionHandler e @ResponseStatus.

    // Annotation que altera o status code devolvido como resposta, BAD_REQUEST = 400
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)

    // Annotation que informa para o Spring que todo erro de argumento no controller, deve ser
    //  direcionado para esse método
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErroDto> handle(MethodArgumentNotValidException exception) {
        List<FormErroDto> erroDto = new ArrayList<>();
        List<FieldError> erros = exception.getBindingResult().getFieldErrors();

        erros.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            FormErroDto erro = new FormErroDto(e.getField(), mensagem);
            erroDto.add(erro);
        });

        return erroDto;
    }
}
