package model;

public class Estoque implements Utilidades {
    private int id_estoque;
    private String nome_estoque;
    private String descricao;
    private int quantidade_estoque;
    
    public Estoque(int id_estoque, String nome_estoque, String descricao, int quantidade_estoque) {
        this.id_estoque = id_estoque;
        this.nome_estoque = nome_estoque;
        this.descricao = descricao;
        this.quantidade_estoque = quantidade_estoque;
    }

    public String exibirTudo() {
        return "ID: " + getId_estoque() + ".\nNome: " + getNome_estoque() + ".\nDescrição: " + getDescricao() + ".\nQuantidade em estoque: " + getQuantidade_estoque() + ".\n";
    }

    public int getId_estoque() {
        return id_estoque;
    }

    public String getNome_estoque() {
        return nome_estoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }
}
