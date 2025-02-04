package model;

// Query sem cpf
public class Endereco implements Utilidades {
    private String cep;
    private String complemento;


    public Endereco(String complemento, String cep) {
        this.cep = cep;
        this.complemento = complemento;
    }

    public String exibirTudo() {
        return "Endereço:\nCep: " + getCep() + ((this.complemento != null) ? "\nComplemento: " + getComplemento() + ".\n" : ".\n");
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }
}