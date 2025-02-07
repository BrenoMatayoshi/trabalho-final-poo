package controller;

import java.util.ArrayList;

import dao.EstoqueDAO;
import model.Estoque;

public class EstoqueController {
    private EstoqueDAO estoqueDAO = new EstoqueDAO();

    public int inserir(Estoque estoque) {
        return estoqueDAO.inserir(estoque);
    }

    public ArrayList<Estoque> getItens() {
        return estoqueDAO.getItens();
    }

    public Estoque getEstoque(int id) {
        return estoqueDAO.getEstoque(id);
    }
    
    public boolean update(Estoque estoque) {
        return estoqueDAO.update(estoque);
    }

    public int quantidadeItens() {
        return estoqueDAO.quantidadeItens();
    }
}
