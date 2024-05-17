package com.fiap.br.controller;

import java.util.List;

import com.fiap.br.models.Usuario;
import com.fiap.br.repositories.UsuarioRepository;
import com.fiap.br.services.QueryExecutor;
import com.fiap.br.services.UsuarioService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController() {
        usuarioService = new UsuarioService(new UsuarioRepository(new QueryExecutor()));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuarioById(@PathParam("id") int id) {
        return usuarioService.getUsuarioById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUsuario(Usuario usuario) {
        usuarioService.createUsuario(usuario);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsuario(@PathParam("id") int id, Usuario usuario) {
        usuario.setId(id);
        usuarioService.updateUsuario(id, usuario);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUsuario(@PathParam("id") int id) {
        usuarioService.deleteUsuario(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
