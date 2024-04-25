package com.fiap.br.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.models.Endereco;
import com.fiap.br.models.pessoa.Funcionario;
import com.fiap.br.models.pessoa.Usuario;

public class FuncionarioRepository extends UsuarioRepository {
    private EnderecoRepository enderecoRepo = new EnderecoRepository();

    @Override
    public List<Usuario> getUsuarios() {
        String sql = "SELECT u.*, f.salario FROM Usuario u INNER JOIN Funcionario f ON u.id_usuario = f.id_funcionario ORDER BY u.nome ASC";
        List<Usuario> usuarios = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(rs.getLong("id_usuario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setTelefone(rs.getString("telefone"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setSalario(rs.getDouble("salario"));

                List<Endereco> enderecos = enderecoRepo.getEnderecosByUsuarioId(rs.getLong("id_usuario"));
                funcionario.setListaEnderecos(enderecos);

                usuarios.add(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override
    public Usuario getUsuarioById(Long id) {
        String sql = "SELECT u.*, f.salario FROM Usuario u INNER JOIN Funcionario f ON u.id_usuario = f.id_funcionario WHERE id_usuario = ?";
        Funcionario funcionario = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setId(rs.getLong("id_usuario"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setTelefone(rs.getString("telefone"));
                    funcionario.setEmail(rs.getString("email"));
                    funcionario.setCpf(rs.getString("cpf"));
                    funcionario.setCargo(rs.getString("cargo"));
                    funcionario.setSenha(rs.getString("senha"));
                    funcionario.setSalario(rs.getDouble("salario"));

                    List<Endereco> enderecos = enderecoRepo.getEnderecosByUsuarioId(rs.getLong("id_usuario"));
                    funcionario.setListaEnderecos(enderecos);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os dados do banco de dados!");
            e.printStackTrace();
        }
        return funcionario;
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        if (!(usuario instanceof Funcionario)) {
            throw new IllegalArgumentException("O objeto passado não é um Funcionario válido.");
        }

        Funcionario funcionario = (Funcionario) usuario;
        String sql = "INSERT INTO Usuario (nome, telefone, email, cpf, cargo, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, 
        new String[]{"id_usuario"})) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getCargo());
            stmt.setString(6, funcionario.getSenha());

            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long idFuncionario = generatedKeys.getLong(1);
                funcionario.setId(idFuncionario);

                enderecoRepo.saveEnderecos(funcionario.getListaEnderecos(), idFuncionario);

                saveFuncionario(funcionario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o funcionário no banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void updateUsuario(Usuario usuario) {
        if (!(usuario instanceof Funcionario)) {
            throw new IllegalArgumentException("O objeto passado não é um Funcionario válido.");
        }

        Funcionario funcionario = (Funcionario) usuario;
        String sql = "UPDATE Usuario SET nome = ?, telefone = ?, email = ?, cpf = ?, cargo = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getTelefone());
            stmt.setString(3, funcionario.getEmail());
            stmt.setString(4, funcionario.getCpf());
            stmt.setString(5, funcionario.getCargo());
            stmt.setLong(6, funcionario.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o funcionário no banco de dados!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUsuario(Long id) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o funcionário do banco de dados!");
            e.printStackTrace();
        }
    }

    private void saveFuncionario(Funcionario funcionario) throws SQLException {
        String sql = "INSERT INTO Funcionario (id_funcionario, salario) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, funcionario.getId());
            stmt.setDouble(2, funcionario.getSalario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao salvar o funcionário no banco de dados!");
            throw e;
        }
    }
}
