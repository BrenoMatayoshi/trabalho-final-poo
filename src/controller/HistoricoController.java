package controller;

import dao.HistoricoDAO;
import model.Historico;

public class HistoricoController {
    private HistoricoDAO historicoDAO = new HistoricoDAO();

    public String inserir(Historico historico) {
        return historicoDAO.inserir(historico);
    }
}
