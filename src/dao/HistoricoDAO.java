package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Historico;

public class HistoricoDAO {
    public String inserir(Historico historico) {
        String sql = "insert into historico(pessoa_cpf, id_estoque, quantidade_historico, data_historico, preco_historico) values(?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historico.getPessoa());
            stmt.setInt(2, historico.getId_estoque());
            stmt.setInt(3, historico.getQuantidade_historico());
            stmt.setDate(4, java.sql.Date.valueOf(historico.getData_historico()));
            stmt.setFloat(5, historico.getPreco_historico());
            int colunasAfetadas = stmt.executeUpdate();
            if (colunasAfetadas == 1) {    
                return "Sucesso.\n";
            }
            return "Mal sucessido.\n";
        } catch (SQLException e) {
            return "Mal sucessido.\n";
        }
    }
}