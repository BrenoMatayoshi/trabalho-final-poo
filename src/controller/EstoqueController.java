package controller;

import dao.EstoqueDAO;
import model.Estoque;

public class EstoqueController {
    private EstoqueDAO estoqueDAO = new EstoqueDAO();

    public int cadastrar(Estoque estoque) {
        return estoqueDAO.cadastrar(estoque);
    }
}
