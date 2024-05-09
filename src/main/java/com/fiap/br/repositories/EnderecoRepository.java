package com.fiap.br.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.models.Endereco;
import com.fiap.br.util.connection.DatabaseConnection;

public class EnderecoRepository {
    private Connection connection = DatabaseConnection.getConnection();

    public List<Endereco> getEnderecosByUsuarioId(Long usuarioId) {
        String sql = "SELECT * FROM Endereco WHERE id_usuario = ?";
        List<Endereco> enderecos = new ArrayList<>();

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setLong(1, usuarioId);
            try (ResultSet rs = psmt.executeQuery()) {
                while (rs.next()) {
                    Endereco endereco = new Endereco();
                    endereco.setId(rs.getLong("id_endereco"));
                    endereco.setRua(rs.getString("rua"));
                    endereco.setNumero(rs.getString("numero"));
                    endereco.setCidade(rs.getString("cidade"));
                    endereco.setEstado(rs.getString("estado"));
                    endereco.setCep(rs.getString("cep"));
                    enderecos.add(endereco);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os endereços do usuário!");
            e.printStackTrace();
        }
        return enderecos;
    }

    public void saveEnderecos(List<Endereco> enderecos, Long idUsuario) {
        String sql = "INSERT INTO Endereco (rua, numero, cidade, estado, cep, id_usuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            for (Endereco endereco : enderecos) {
                psmt.setString(1, endereco.getRua());
                psmt.setString(2, endereco.getNumero());
                psmt.setString(3, endereco.getCidade());
                psmt.setString(4, endereco.getEstado());
                psmt.setString(5, endereco.getCep());
                psmt.setLong(6, idUsuario);

                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar os endereços no banco de dados!");
            e.printStackTrace();
        }
    }

    public void updateEndereco(Endereco endereco) {
        String sql = "UPDATE Endereco SET rua = ?, numero = ?, cidade = ?, estado = ?, cep = ? WHERE id_endereco = ?";

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, endereco.getRua());
            psmt.setString(2, endereco.getNumero());
            psmt.setString(3, endereco.getCidade());
            psmt.setString(4, endereco.getEstado());
            psmt.setString(5, endereco.getCep());
            psmt.setLong(6, endereco.getId());

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Nenhum registro de endereço foi atualizado.");
            } else {
                System.out.println("Endereço atualizado com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o endereço no banco de dados!");
            e.printStackTrace();
        }
    }

}
