package com.fiap.br.repositories;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.connection.DatabaseConnection;
import com.fiap.br.models.EmpresaCliente;
import com.fiap.br.models.pessoa.Cliente;

public class EmpresaClienteRepository {
    private Connection connection = DatabaseConnection.getConnection();
    private UsuarioRepository clienteRepository = new ClienteRepository();

    public List<EmpresaCliente> getEmpresasClientes() {
        List<EmpresaCliente> empresasClientes = new ArrayList<>();
        String sql = "SELECT * FROM EmpresaCliente";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmpresaCliente empresaCliente = new EmpresaCliente();
                empresaCliente.setId(rs.getLong("id_empresaCliente"));
                empresaCliente.setCnpj(rs.getString("cnpj"));
                empresaCliente.setTelefone(rs.getString("telefone"));
                empresaCliente.setRazaoSocial(rs.getString("razaoSocial"));
                empresaCliente.setNomeFantasia(rs.getString("nomeFantasia"));
                empresaCliente.setTamanho(rs.getInt("tamanho"));
                empresaCliente.setCliente((Cliente) clienteRepository.getUsuarioById(rs.getLong("id_cliente")));

                empresasClientes.add(empresaCliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empresas clientes do banco de dados!");
            e.printStackTrace();
        }

        return empresasClientes;
    }

    public EmpresaCliente getEmpresaClienteById(Long id) {
        String sql = "SELECT * FROM EmpresaCliente WHERE id_empresaCliente = ?";
        EmpresaCliente empresaCliente = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empresaCliente = new EmpresaCliente();
                    empresaCliente.setId(rs.getLong("id_empresaCliente"));
                    empresaCliente.setCnpj(rs.getString("cnpj"));
                    empresaCliente.setTelefone(rs.getString("telefone"));
                    empresaCliente.setRazaoSocial(rs.getString("razaoSocial"));
                    empresaCliente.setNomeFantasia(rs.getString("nomeFantasia"));
                    empresaCliente.setTamanho(rs.getInt("tamanho"));
                    empresaCliente.setCliente((Cliente) clienteRepository.getUsuarioById(rs.getLong("id_cliente")));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar empresa cliente pelo ID do banco de dados!");
            e.printStackTrace();
        }

        return empresaCliente;
    }

    public void saveEmpresaCliente(EmpresaCliente empresaCliente) {
        String sql = "INSERT INTO EmpresaCliente (cnpj, telefone, razaoSocial, nomeFantasia, tamanho, id_cliente) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, empresaCliente.getCnpj());
            stmt.setString(2, empresaCliente.getTelefone());
            stmt.setString(3, empresaCliente.getRazaoSocial());
            stmt.setString(4, empresaCliente.getNomeFantasia());
            stmt.setInt(5, empresaCliente.getTamanho());
            stmt.setLong(6, empresaCliente.getCliente().getId());

            stmt.executeUpdate();
            System.out.println("Empresa cliente salva com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar empresa cliente no banco de dados!");
            e.printStackTrace();
        }
    }

    public void deleteEmpresaCliente(Long id) {
        String sql = "DELETE FROM EmpresaCliente WHERE id_empresaCliente = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            System.out.println("Empresa cliente excluída com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao excluir empresa cliente do banco de dados!");
            e.printStackTrace();
        }
    }

}
