package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Endereco;

public class EnderecoDAO {
    public boolean inserirPessoa(Endereco endereco, String cpf) {
        String sql = "insert into endereco values(?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, endereco.getComplemento());
            stmt.setString(3, endereco.getCep());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
