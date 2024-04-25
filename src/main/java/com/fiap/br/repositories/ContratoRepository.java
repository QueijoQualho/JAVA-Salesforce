package com.fiap.br.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.connection.DatabaseConnection;
import com.fiap.br.models.Contrato;
import com.fiap.br.models.EmpresaCliente;
import com.fiap.br.models.Plano;
import com.fiap.br.models.enums.TipoPagamento;
import com.fiap.br.models.pessoa.Funcionario;

public class ContratoRepository {
    private Connection connection = DatabaseConnection.getConnection();
    private UsuarioRepository funcionarioRepository = new FuncionarioRepository();
    private EmpresaClienteRepository empresaClienteRepository = new EmpresaClienteRepository();
    private PlanoRepository planoRepository = new PlanoRepository();

    public void saveContrato(Contrato contrato) {
        String sql = "INSERT INTO Contrato (funcionario_id, empresa_cliente_id, plano_id, tipoPagamento) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, contrato.getFuncionario().getId()); 
            stmt.setLong(2, contrato.getEmpresa().getId());
            stmt.setLong(3, contrato.getPlano().getId());
            stmt.setString(4, contrato.getTipoPagamento().toString());

            stmt.executeUpdate();

            System.out.println("Contrato Salvo");
        } catch (SQLException e) {
            System.out.println("Erro ao salvar contrato no banco de dados!");
            e.printStackTrace();
        }
    }

    public List<Contrato> getAllContratos() {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT * FROM Contrato";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rs.getLong("id_contrato"));

                Plano plano = planoRepository.getPlanoById(rs.getLong("plano_id"));
                contrato.setPlano(plano);

                EmpresaCliente empresa = empresaClienteRepository.getEmpresaClienteById(rs.getLong("empresa_cliente_id"));
                contrato.setEmpresa(empresa);

                Funcionario funcionario = (Funcionario) funcionarioRepository.getUsuarioById(rs.getLong("funcionario_id"));
                contrato.setFuncionario(funcionario);

                contrato.setTipoPagamento(TipoPagamento.CREDITO);

                contratos.add(contrato);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar contratos do banco de dados!");
            e.printStackTrace();
        }

        return contratos;
    }

    public Contrato getContratoById(long contratoId) {
        String sql = "SELECT * FROM Contrato WHERE id_contrato = ?";
        Contrato contrato = null;
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, contratoId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                contrato = new Contrato();
                contrato.setId(rs.getLong("id_contrato"));
    
                Plano plano = planoRepository.getPlanoById(rs.getLong("plano_id"));
                contrato.setPlano(plano);
    
                EmpresaCliente empresa = empresaClienteRepository.getEmpresaClienteById(rs.getLong("empresa_cliente_id"));
                contrato.setEmpresa(empresa);
    
                Funcionario funcionario = (Funcionario) funcionarioRepository.getUsuarioById(rs.getLong("funcionario_id"));
                contrato.setFuncionario(funcionario);
    
                contrato.setTipoPagamento(TipoPagamento.CREDITO);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar contrato pelo ID no banco de dados!");
            e.printStackTrace();
        }
    
        return contrato;
    }
    
    public void updateContrato(Contrato contrato) {
        String sql = "UPDATE Contrato SET funcionario_id = ?, empresa_cliente_id = ?, plano_id = ? WHERE id_contrato = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, contrato.getFuncionario().getId());
            stmt.setLong(2, contrato.getEmpresa().getId());
            stmt.setLong(3, contrato.getPlano().getId());
            stmt.setLong(4, contrato.getId());
    
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar contrato no banco de dados!");
            e.printStackTrace();
        }
    }
    
    public void deleteContrato(long contratoId) {
        String sql = "DELETE FROM Contrato WHERE id_contrato = ?";
    
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, contratoId);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Erro ao deletar contrato do banco de dados!");
            e.printStackTrace();
        }
    }
    
}
