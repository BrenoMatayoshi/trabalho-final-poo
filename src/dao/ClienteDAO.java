package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
    public boolean criarCliente(Cliente cliente) {
                String sql = "insert into cliente(nome_empresa, cnpj, nome_responsavel, cpf) values(?, ?, ?, ?);";
        try (Connection conn = ConexaoDAO.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNomeEmpresa());
            stmt.setString(2, cliente.getCnpj());
            stmt.setString(3, cliente.getNomePessoa());
            stmt.setString(4, cliente.getCpf());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Cliente> getClientes() {
        ArrayList<Cliente> arrayList = new ArrayList<>();
        String sql = "select * from cliente";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                arrayList.add(new Cliente(rs.getInt("id_cliente"), rs.getString("cpf"), rs.getString("nome_responsavel"), rs.getString("nome_empresa"), rs.getString("cnpj")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return arrayList;
    }

    public Cliente getCliente(int id) {
        String sql = "select * from cliente where id_cliente = ?";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(rs.getInt("id_cliente"), rs.getString("cpf"), rs.getString("nome_responsavel"), rs.getString("nome_empresa"), rs.getString("cnpj"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
