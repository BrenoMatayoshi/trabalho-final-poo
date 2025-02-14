package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Historico;

public class HistoricoDAO {
    public String inserir(Historico historico) {
        String sql = "insert into historico(pessoa_cpf, id_estoque, quantidade_historico, data_historico, preco_historico, id_cliente) values(?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDAO.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, historico.getPessoa());
            stmt.setInt(2, historico.getId_estoque());
            stmt.setInt(3, historico.getQuantidade_historico());
            stmt.setDate(4, java.sql.Date.valueOf(historico.getData_historico()));
            stmt.setFloat(5, historico.getPreco_historico());
            if (historico.getId_cliente() == 0) {
                stmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(6, historico.getId_cliente());
            }
            int colunasAfetadas = stmt.executeUpdate();
            if (colunasAfetadas == 1) {    
                return "Sucesso.\n";
            }
            return "Mal sucedido.\n";
        } catch (SQLException e) {
            return "Mal sucedido.\n";
        }
    }

    public ArrayList<Historico> getHistorico() {
        ArrayList<Historico> arrayList = new ArrayList<>();
        String sql = "select h.id_historico, p.nome_pessoa, e.nome_estoque, h.quantidade_historico, h.data_historico, h.preco_historico, c.nome_empresa from historico h left join pessoa p on p.cpf = h.pessoa_cpf left join estoque e on e.id_estoque = h.id_estoque left join cliente c on c.id_cliente = h.id_cliente";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                arrayList.add(new Historico(rs.getString("nome_pessoa"), rs.getString("nome_estoque"), rs.getInt("quantidade_historico"), rs.getFloat("preco_historico"), rs.getDate("data_historico").toLocalDate(), rs.getString("nome_empresa")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrayList;
    }
}