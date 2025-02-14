package model;

import java.time.LocalDate;

public class Usuario extends Pessoa implements Utilidades {
    private String senhaUsuario;
    private String email;
    private int id_cargo;
    private Endereco endereco;
    
    // construtor para criar uma pessoa.
    public Usuario(String cpf, String nome_pessoa, LocalDate data_nascimento, String senhaUsuario, String email, int id_cargo, Endereco endereco) {
        super(cpf, nome_pessoa, data_nascimento);
        this.senhaUsuario = senhaUsuario;
        this.email = email;
        this.id_cargo = id_cargo;
        this.endereco = endereco;
    }

    // contrutor para login.
    public Usuario(String cpf, String nome_pessoa, int id_cargo) {
        super(cpf, nome_pessoa);
        this.id_cargo = id_cargo;
    }

    // exibirTudo() não exibira a senha nem o cpf.
    public String exibirTudo() {
        return "Nome: " + super.getNomePessoa() + ".\nCargo: " + getIdCargo() + "Data de nascimento: " + getData_nascimento() + ".\n" + endereco.exibirTudo();
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public String getEmail() {
        return email;
    }

    public int getIdCargo() {
        return id_cargo;
    }
}