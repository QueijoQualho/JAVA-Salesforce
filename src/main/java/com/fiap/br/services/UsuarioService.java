package com.fiap.br.services;

import java.util.List;

import com.fiap.br.models.Usuario;
import com.fiap.br.repositories.UsuarioRepository;
import com.fiap.br.util.validator.FieldValidator;

public class UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll(Usuario.class);
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findOne(Usuario.class, id);
    }

    public boolean createUsuario(Usuario usuario) {
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

    public boolean updateUsuario(int id, Usuario usuario) {
        try {
            boolean isValid = FieldValidator.requiredFieldsFilled(usuario);

            if (!isValid) {
                return false;
            }

            usuarioRepository.update(usuario, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteUsuario(int id) {
        usuarioRepository.delete(Usuario.class, id);
    }

}
