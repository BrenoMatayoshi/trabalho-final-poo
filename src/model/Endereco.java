package model;

// Query sem cpf
public class Endereco implements Utilidades {
    private String rua;
    private String bairro;
    private int numero;
    private String complemento;
    private String estado;
    private String cidade;

    public Endereco(String rua, String bairro, String complemento, int numero, String cidade, String estado) {
        this.rua = rua;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String exibirTudo() {
        return "Endereço:\nEstado: " + getEstado() + ".\nCidade: " + getCidade() + ".\nBairro: " + getBairro() + ".\nRua: " + getRua() + ".\nNúmero: " + getNumero() + ((this.complemento != null) ? "\nComplemento: " + getComplemento() + ".\n" : ".\n");
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public int getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }
}