package controller;

import dao.EnderecoDAO;
import model.Endereco;

public class EnderecoController {
    private EnderecoDAO enderecoDAO = new EnderecoDAO();
    public Boolean inserirEndereco(Endereco endereco, String cpf) {
        return enderecoDAO.inserirPessoa(endereco, cpf);
    }
}
