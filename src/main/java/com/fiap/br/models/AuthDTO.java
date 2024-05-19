package com.fiap.br.models;

import com.fiap.br.util.annotations.Required;

import lombok.Data;

@Data
public class AuthDTO {
    @Required
    private String email;
    @Required
    private String senha;
}
