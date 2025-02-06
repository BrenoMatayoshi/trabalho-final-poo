package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Historico;

public class HistoricoDAO {
    public String inserir(Historico historico) {
        String sql = "insert into historico(pessoa_cpf, id_estoque, data_historico) values(?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historico.getPessoa());
            stmt.setInt(2, historico.getId_estoque());
            stmt.setDate(3, java.sql.Date.valueOf(historico.getData_historico()));
            stmt.executeUpdate();
            return "Cadastro bem sucedido.\n";
        } catch (SQLException e) {
            return "Cadastro mal sucedido.\n";
        }
    }
}