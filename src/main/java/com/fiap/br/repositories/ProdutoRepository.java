package com.fiap.br.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fiap.br.connection.DatabaseConnection;
import com.fiap.br.models.Plano;
import com.fiap.br.models.Produto.Produto;

public class ProdutoRepository {
    private Connection connection = DatabaseConnection.getConnection();

    public List<Produto> getProdutosPorPlano(Long idPlano) {
        String sql = "SELECT * FROM Produto WHERE id_plano = ?";
        List<Produto> produtos = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setLong(1, idPlano);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getLong("id_produto"));
                    produto.setNome(rs.getString("nome"));
                    produto.setPreco(rs.getDouble("preco"));

                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public List<Produto> getAllProdutos() {
    String sql = "SELECT * FROM Produto";
    List<Produto> produtos = new ArrayList<>();

    try (PreparedStatement pstm = connection.prepareStatement(sql);
         ResultSet rs = pstm.executeQuery()) {

        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getLong("id_produto"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));

            produtos.add(produto);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return produtos;
}

    public void saveProduto(List<Produto> produtos, Long idPlano) {
        String sql = "INSERT INTO Produto (nome, preco, id_plano) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            for (Produto produto : produtos) {
                pstm.setString(1, produto.getNome());
                pstm.setDouble(2, produto.getPreco());
                pstm.setLong(3, idPlano);
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProdutosPlano(Plano plano) {
        String sql = "UPDATE Produto SET nome = ?, preco = ? WHERE id_produto = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {

            for (Produto produto : plano.getListaProdutos()) {
                pstm.setString(1, produto.getNome());
                pstm.setDouble(2, produto.getPreco());
                pstm.setLong(3, produto.getId());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
