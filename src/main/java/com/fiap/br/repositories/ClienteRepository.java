package com.fiap.br.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.models.Contrato;
import com.fiap.br.models.Endereco;
import com.fiap.br.models.Usuario;

public class ClienteRepository {
    private EnderecoRepository enderecoRepo = new EnderecoRepository();

    public List<Usuario> getUsuarios() {
        String sql = "SELECT * from Usuario";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql);
                ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Usuario newCliente = new Usuario();

                newCliente.setId(rs.getLong("id_usuario"));
                newCliente.setNome(rs.getString("nome"));
                newCliente.setTelefone(rs.getString("telefone"));
                newCliente.setEmail(rs.getString("email"));
                newCliente.setCpf(rs.getString("cpf"));
                newCliente.setCargo(rs.getString("cargo"));
                newCliente.setSenha(rs.getString("senha"));
                newCliente.setIsAdmin(rs.getBoolean("isAdmin"));


                List<Endereco> enderecos = enderecoRepo.getEnderecosByUsuarioId(rs.getLong("id_usuario"));
                newCliente.setListaEnderecos(enderecos);

                usuarios.add(newCliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return usuarios;
    }

    public Usuario getUsuarioById(Long id) {
        String sql = "SELECT * FROM Usuario WHERE id_usuario = ? ";
        Usuario cliente = null;
    
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    cliente = new Usuario();
                    cliente.setId(rs.getLong("id_usuario"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setCargo(rs.getString("cargo"));
                    cliente.setSenha(rs.getString("senha"));
                    cliente.setIsAdmin(rs.getBoolean("isAdmin"));

                    List<Endereco> enderecos = enderecoRepo.getEnderecosByUsuarioId(rs.getLong("id_usuario"));
                    cliente.setListaEnderecos(enderecos);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return cliente;
    }
    
    public void saveUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario (nome, telefone, email, cpf, cargo, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, new String[] { "id_usuario" })) {
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getTelefone());
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getCpf());
            pstm.setString(5, usuario.getCargo());
            pstm.setString(6, usuario.getSenha());

            pstm.executeUpdate();

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long idCliente = generatedKeys.getLong(1);
                usuario.setId(idCliente);
                enderecoRepo.saveEnderecos(usuario.getListaEnderecos(), idCliente);
            }
        } catch (Exception e) {
            System.out.println("Erro ao salvar o usuário213123 no banco de dados!");
            e.printStackTrace();
        }
    }

    public void updateUsuario(Usuario usuario) {

        String sql = "UPDATE Usuario SET nome = ?, telefone = ?, email = ?, cargo = ? WHERE id_usuario = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, usuario.getNome());
            pstm.setString(2, usuario.getTelefone());
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getCargo());
            pstm.setLong(5, usuario.getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o cliente no banco de dados!");
            e.printStackTrace();
        }
    }

    public void deleteUsuario(Long id) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o cliente do banco de dados!");
            e.printStackTrace();
        }
    }
   
}
