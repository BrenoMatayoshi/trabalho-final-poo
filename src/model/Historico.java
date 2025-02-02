package model;

import java.time.LocalDate;
// é necessario fazer uma querry que retorne o nome do item ao inves do id e retorne o nome da pessoa ao inves do seu cpf
public class Historico implements Utilidades {
    private int id_historico;
    private String nome_pessoa;
    private String nome_estoque;
    private int quantidade_historico;
    private LocalDate data_historico;
    private float preco_historico;

    public Historico(int id_historico, String nome_pessoa, String nome_estoque, int quantidade_historico, float preco_historico) {
        this.id_historico = id_historico;
        this.nome_pessoa = nome_pessoa;
        this.nome_estoque = nome_estoque;
        this.quantidade_historico = quantidade_historico;
        this.data_historico = LocalDate.now();
        this.preco_historico = preco_historico;
    }

    public String exibirTudo() {
        return "Nome: " + getnome_estoque() + ((this.quantidade_historico > 0) ? "Foram adicionados: +" : "Foram removidos: ") + getQuantidade_historico() + ".\nPor: " + getNome_pessoa() + " em " + getData_historico() + ".\n";
    }

    public int getId_historico() {
        return id_historico;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
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

    public float getPreco_historico() {
        return preco_historico;
    }
}