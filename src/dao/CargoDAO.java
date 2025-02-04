package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cargo;

public class CargoDAO {
    public Cargo getCargos() {
        Cargo cargo = new Cargo();
        String sql = "select * from cargo";
        try (Connection conn = ConexaoDAO.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cargo.adicionarItem(rs.getInt("id_cargo"), rs.getString("nome_cargo"));
            }
            stmt.close();
            conn.close();
            return cargo;
        } catch (SQLException e) {
            System.out.println("Erro ao se conectar ao banco de dados.\n");
        }
        return null;
    }
}
