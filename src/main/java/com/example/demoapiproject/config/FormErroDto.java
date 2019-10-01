package com.example.demoapiproject.config;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FormErroDto {

    private String campo;
    private String erro;

    public FormErroDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }
}
