package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Estoque;

public class EstoqueDAO {
    public int cadastrar(Estoque estoque) {
        String sql = "insert into estoque(nome_estoque, descricao) values(?, ?)";
        // Em conn.prepareStatement o atributo Statement.RETURN_GENERATED_KEYS configura o stmt para retornar a quantidade de linhas modificadas
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, estoque.getNome_estoque());
            stmt.setString(2, estoque.getDescricao());
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
}
