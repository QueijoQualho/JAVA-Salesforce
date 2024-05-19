package com.fiap.br.services;

import com.fiap.br.models.AuthDTO;
import com.fiap.br.models.Usuario;
import com.fiap.br.repositories.UsuarioRepository;
import com.fiap.br.util.validator.FieldValidator;

public class AuthService {
    private UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Boolean login(AuthDTO authDto) {
        Usuario user = usuarioRepository.findByEmail(authDto.getEmail());

        if (user == null) {
            return false;
        }

        if (!user.getSenha().equals(authDto.getSenha())) {
            return false;
        }

        return true;
    }

    public boolean signup(Usuario usuario) {
        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(usuario);

            if (!isValid) {
                return false;
            }

            usuarioRepository.save(usuario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
