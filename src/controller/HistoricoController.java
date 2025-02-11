package controller;

import java.util.ArrayList;

import dao.HistoricoDAO;
import model.Historico;

public class HistoricoController {
    private HistoricoDAO historicoDAO = new HistoricoDAO();

    public String inserir(Historico historico) {
        return historicoDAO.inserir(historico);
    }

    public ArrayList<Historico> getHistorico() {
        return historicoDAO.getHistorico();
    }
}
