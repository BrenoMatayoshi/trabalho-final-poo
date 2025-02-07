package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Estoque;

public class EstoqueDAO {
    public int inserir(Estoque estoque) {
        String sql = "insert into estoque(nome_estoque, descricao, quantidade_estoque) values(?, ?, ?)";
        // Em conn.prepareStatement o atributo Statement.RETURN_GENERATED_KEYS configura o stmt para retornar a quantidade de linhas modificadas
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, estoque.getNome_estoque());
            stmt.setString(2, estoque.getDescricao());
            stmt.setInt(3, estoque.getQuantidade_estoque());
            int colunas = stmt.executeUpdate();
            if (colunas > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Retorna o ID gerado
                    }
                }
            }
            return -1;
        } catch (SQLException e) {
            return -1;
        }
    }

    public ArrayList<Estoque> getItens() {
        ArrayList<Estoque> arrayList = new ArrayList<>();
        String sql = "select * from estoque";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                arrayList.add(new Estoque(rs.getInt("id_estoque"), rs.getString("nome_estoque"), rs.getString("descricao"), rs.getInt("quantidade_estoque")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrayList;
    }

    public Estoque getEstoque(int id) {
        String sql = "select * from estoque where id_estoque = ?";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estoque(rs.getInt("id_estoque"), rs.getString("nome_estoque"), rs.getString("descricao"), rs.getInt("quantidade_estoque"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean update(Estoque estoque) {
        String sql = "UPDATE estoque SET quantidade_estoque = ? WHERE id_estoque = ?";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, estoque.getQuantidade_estoque());
            stmt.setInt(2, estoque.getId_estoque());
            int colunasAfetadas = stmt.executeUpdate();
            
            return colunasAfetadas == 1;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public int quantidadeItens() {
        String sql = "select (*) from estoque";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1);
        } catch (SQLException e) {
            return 0;
        }
    }
}