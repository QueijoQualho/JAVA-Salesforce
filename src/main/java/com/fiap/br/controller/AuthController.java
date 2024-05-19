package com.fiap.br.controller;

import com.fiap.br.models.AuthDTO;
import com.fiap.br.models.Usuario;
import com.fiap.br.repositories.UsuarioRepository;
import com.fiap.br.services.AuthService;
import com.fiap.br.services.QueryExecutor;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthController {
    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService(new UsuarioRepository(new QueryExecutor()));
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(AuthDTO authDTO) {
        Boolean logado = authService.login(authDTO);

        if (!logado) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário ou senha incorretos").build();
        }

        return Response.ok().entity("Login realizado com sucesso").build();
    }

    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signup(Usuario usuario) {
        boolean isValid = authService.signup(usuario);
        if (!isValid) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).build();
    }
}
