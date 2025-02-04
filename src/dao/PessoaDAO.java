package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pessoa;

public class PessoaDAO {
    public boolean inserirPessoa(Pessoa pessoa) {
        String sql = "insert into pessoa values(?, ?, ?, ?, ?, ?);";
        try (Connection conn = ConexaoDAO.getConnection(); 
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getCpf());
            stmt.setString(2, pessoa.getNome_pessoa());
            stmt.setDate(3, Date.valueOf(pessoa.getData_nascimento()));
            stmt.setString(4, pessoa.getSenha_pessoa());
            stmt.setString(5, pessoa.getEmail());
            stmt.setInt(6, pessoa.getCargo());
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // public String removerPessoa(Pessoa pessoa) {}

    public Pessoa login(String login, String senha) {
        String sql = "select cpf, nome_pessoa, id_cargo from pessoa where (cpf = ? or email = ?) and senha_pessoa = ?";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, login);
            stmt.setString(3, senha);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            conn.close();
            if (rs.next()) {
                return new Pessoa(rs.getString("cpf"),rs.getString("nome_pessoa"), rs.getInt("id_cargo"));
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean existeEmail(String email) {
        String sql = "select email from pessoa where email = ?";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            conn.close();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean existeCpf(String cpf) {
        String sql = "select cpf from pessoa where cpf = ?";
        try (Connection conn = ConexaoDAO.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            stmt.close();
            conn.close();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }
}
