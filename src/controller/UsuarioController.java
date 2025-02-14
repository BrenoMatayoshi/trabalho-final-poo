package controller;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public boolean inserirPessoa(Usuario pessoa) {
        return usuarioDAO.inserirUsuario(pessoa);
    }

    public Usuario login(String login, String senha) {
        return usuarioDAO.login(login, senha);
    }

    public boolean existeCpf(String cpf) {
        return usuarioDAO.existeCpf(cpf);
    }

    public boolean existeEmail(String email) {
        return usuarioDAO.existeEmail(email);
    }
}
