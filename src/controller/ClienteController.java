package controller;

import java.util.ArrayList;

import dao.ClienteDAO;
import model.Cliente;

public class ClienteController {
    private ClienteDAO clienteDAO = new ClienteDAO();
    public boolean criarCliente(Cliente cliente) {
        return clienteDAO.criarCliente(cliente);
    }

    public ArrayList<Cliente> getClientes() {
        return clienteDAO.getClientes();
    }

    public Cliente getCliente(int id) {
        return clienteDAO.getCliente(id);
    }
}
