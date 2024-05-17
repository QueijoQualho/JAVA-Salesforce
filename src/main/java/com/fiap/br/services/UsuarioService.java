package com.fiap.br.services;

import java.util.List;

import com.fiap.br.models.Usuario;
import com.fiap.br.repositories.UsuarioRepository;

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

    public void createUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void updateUsuario(int id, Usuario usuario) {
        usuario.setId(id);
        usuarioRepository.update(usuario, id);
    }

    public void deleteUsuario(int id) {
        usuarioRepository.delete(Usuario.class, id);
    }

}
