package controller;

import dao.CargoDAO;
import model.Cargo;

public class CargoController {
    private CargoDAO cargoDAO = new CargoDAO();

    public Cargo getCargos() {
        return cargoDAO.getCargos();
    }
}
