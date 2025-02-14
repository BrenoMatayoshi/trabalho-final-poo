package model;

import java.time.LocalDate;

public class Pessoa {
    private String cpf;
    private String nome_pessoa;
    private LocalDate data_nascimento;

    Pessoa(String cpf, String nome_pessoa, LocalDate data_nascimento) {
        this.cpf = cpf;
        this.nome_pessoa = nome_pessoa;
        this.data_nascimento = data_nascimento;
    }

    Pessoa(String cpf, String nome_pessoa) {
        this.cpf = cpf;
        this.nome_pessoa = nome_pessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNomePessoa() {
        return nome_pessoa;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }
}
