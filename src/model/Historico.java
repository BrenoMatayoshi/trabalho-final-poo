package model;

import java.time.LocalDate;
// para printar é necessario fazer uma querry que retorne o nome da pessoa ao inves do seu cpf
public class Historico implements Utilidades {
    private int id_historico;
    private String pessoa;
    private int id_estoque;
    private String nome_estoque;
    private int quantidade_historico;
    private LocalDate data_historico;
    private Float preco_historico;
    private int id_cliente;
    private String cliente;

    // Contrutor para inserir item no historico, passa o cpf na variavel pessoa
    public Historico(String pessoa, int id_estoque, int quantidade_historico, float preco_historico, int id_cliente) {
        this.pessoa = pessoa;
        this.data_historico = LocalDate.now();
        this.id_estoque = id_estoque;
        this.quantidade_historico = quantidade_historico;
        this.preco_historico = preco_historico;
        this.id_cliente = id_cliente;
    }
    
    // Construtor para printar item, passa o nome da pessoa na variavel pessoa
    public Historico(String pessoa, String nome_estoque, int quantidade_historico, float preco_historico, LocalDate data_historico, String cliente) {
        this.pessoa = pessoa;
        this.nome_estoque = nome_estoque;
        this.quantidade_historico = quantidade_historico;
        this.data_historico = data_historico;
        this.preco_historico = preco_historico;
        this.cliente = cliente;
    }
    
    public String exibirTudo() {
        return "Nome: " + getnome_estoque() + ((getQuantidade_historico() == 0) ? ".\nCadastro do item" : ((this.quantidade_historico > 0) ? ".\nForam adicionados: +" + getQuantidade_historico() : ".\nForam removidos: " + getQuantidade_historico()))  + ".\nPor: " + getPessoa() + " em " + getData_historico() + ((getCliente() == null) ? ".\n" : ".\nCliente: " + getCliente() + ".\n");
    }

    public int getId_historico() {
        return id_historico;
    }

    public String getPessoa() {
        return pessoa;
    }

    public int getId_estoque() {
        return id_estoque;
    }

    public String getnome_estoque() {
        return nome_estoque;
    }

    public int getQuantidade_historico() {
        return quantidade_historico;
    }

    public LocalDate getData_historico() {
        return data_historico;
    }

    public Float getPreco_historico() {
        return preco_historico;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public String getCliente() {
        return cliente;
    }
}