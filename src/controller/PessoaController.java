package controller;

import dao.PessoaDAO;
import model.Pessoa;

public class PessoaController {
    private PessoaDAO pessoaDAO = new PessoaDAO();
    
    public boolean inserirPessoa(Pessoa pessoa) {
        return pessoaDAO.inserirPessoa(pessoa);
    }

    public Pessoa login(String login, String senha) {
        return pessoaDAO.login(login, senha);
    }

    public boolean existeCpf(String cpf) {
        return pessoaDAO.existeCpf(cpf);
    }

    public boolean existeEmail(String email) {
        return pessoaDAO.existeEmail(email);
    }
}
