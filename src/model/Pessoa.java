package model;

import java.time.LocalDate;

public class Pessoa implements Utilidades {
    private String cpf;
    private String nome_pessoa;
    private LocalDate data_nascimento;
    private String senha_pessoa;
    private String email;
    private int id_cargo;
    private Endereco endereco;
    
    // construtor para criar uma pessoa.
    public Pessoa(String cpf, String nome_pessoa, LocalDate data_nascimento, String senha_pessoa, String email, int id_cargo, Endereco endereco) {
        this.cpf = cpf;
        this.nome_pessoa = nome_pessoa;
        this.data_nascimento = data_nascimento;
        this.senha_pessoa = senha_pessoa;
        this.email = email;
        this.id_cargo = id_cargo;
        this.endereco = endereco;
    }

    // contrutor para login.
    public Pessoa(String cpf, String nome_pessoa, int id_cargo) {
        this.cpf = cpf;
        this.nome_pessoa = nome_pessoa;
        this.id_cargo = id_cargo;
    }

    // exibirTudo() não exibira a senha nem o cpf.
    public String exibirTudo() {
        return "Nome: " + getNome_pessoa() + ".\nCargo: " + getCargo() + "Data de nascimento: " + getData_nascimento() + ".\n" + endereco.exibirTudo();
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome_pessoa() {
        return nome_pessoa;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public String getSenha_pessoa() {
        return senha_pessoa;
    }

    public String getEmail() {
        return email;
    }

    public int getCargo() {
        return id_cargo;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}