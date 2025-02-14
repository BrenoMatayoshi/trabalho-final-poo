package model;

public class Cliente extends Pessoa implements Utilidades {
    private String nomeEmpresa;
    private String cnpj;
    private int id_cliente;

    public Cliente(int id_cliente, String cpf, String nomeCliente, String nomeEmpresa, String cnpj) {
        super(cpf, nomeCliente);
        this.id_cliente = id_cliente;
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
    }

    // Construtor par cadastro de empresa
    public Cliente(String cpf, String nomeCliente, String nomeEmpresa, String cnpj) {
        super(cpf, nomeCliente);
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
    }

    public String exibirTudo() {
        return "Id: " + getId_cliente() + ".\nNome do responsável: " + super.getNomePessoa() + ".\nNome da empresa: " + nomeEmpresa + ".\nCNPJ: " + cnpj + ".\n";
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }
    
    public int getId_cliente() {
        return id_cliente;
    }
}
