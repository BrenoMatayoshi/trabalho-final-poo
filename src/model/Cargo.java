package model;

import java.util.HashMap;

public class Cargo {
    private HashMap<Integer, String> dicionario;

    public Cargo() {
        dicionario = new HashMap<>();
    }

    public void adicionarItem(int chave, String valor) {
        dicionario.put(chave, valor);
    }

    private String verificarChave(int chave) {
        return dicionario.get(chave);
    }

    public boolean verificarCargo(int cargo, String cargoEsperado) {
        if (verificarChave(cargo) != cargoEsperado) {
            return false;
        }
        return true;
    }
}
