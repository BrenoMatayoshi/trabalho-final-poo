package view;

import java.time.LocalDate;
import java.util.Scanner;

import controller.EnderecoController;
import controller.EstoqueController;
import controller.HistoricoController;
import controller.PessoaController;
import model.Endereco;
import model.Estoque;
import model.Historico;
import model.Pessoa;

public class App {
    static PessoaController pessoaController = new PessoaController();
    static EnderecoController enderecoController = new EnderecoController();
    static EstoqueController estoqueController = new EstoqueController();
    static HistoricoController historicoController = new HistoricoController();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pessoa usuarioAtivo = menuLogin(scanner);
        if (usuarioAtivo == null) {
            return;
        }
        while (true) {
            System.out.println();
            try {
                System.out.println(Auxiliar.menuPrincipal(usuarioAtivo.getCargo()));
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.println(cadastrarItem(scanner, usuarioAtivo));
                        break;
                
                    case 2:
                        
                        break;
                
                    case 3:
                        
                        break;
                
                    case 4:
                        
                        break;
                
                    case 5:
                        
                        break;
                
                    case 6:
                        
                        break;
                
                    case 0:
                        System.out.println("Encerrando o programa.");
                        return;
                
                    default:
                        System.out.println("Opção inválida.\n");
                        break;
                }
            } catch (Exception e) {
                scanner.nextLine();
                //System.out.println("Opção inválida.\n");
            }
        }
    }

    public static Pessoa menuLogin(Scanner scanner) {
        while (true) {
            System.out.println("1 - Login.\n2 - Registrar.\n3 - Sair.\n");
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        String login;
                        Auxiliar.limparTela();
                        while (true) {
                            while (true) {
                                System.out.println("Login:\nInforme seu email ou cpf: ");
                                login = scanner.nextLine();

                                if (Auxiliar.validarEmail(login)) {
                                    break;
                                }
                                if (Auxiliar.validarCPF(login)) {
                                    login = Auxiliar.formatarCPF(login);
                                    break;
                                }
                                System.out.println("Login inválido.\n");
                            }
                            System.out.println("Informe sua senha: ");
                            String senha = scanner.nextLine();
                            Pessoa usuario = pessoaController.login(login, senha);
                            if (usuario != null) {
                                System.out.println("Login bem sucedido.\n");
                                Thread.sleep(5000);
                                Auxiliar.limparTela();
                                return usuario;
                            }
                            System.out.println("Login ou senha inválidos, tente novamente.\n");
                        }

                case 2:
                    Auxiliar.limparTela();
                    System.out.println("Registrar:\nInforme seu nome: ");
                    String nome = scanner.nextLine();
                    System.out.println("Informe seu cpf: ");
                    String cpf = null;
                    while (true) {
                        cpf = scanner.nextLine();
                        if (Auxiliar.validarCPF(cpf)) {
                            cpf = Auxiliar.formatarCPF(cpf);
                            break;
                        }
                        System.out.println("Cpf inválido, tente novamente.\n");
                    }
                    System.out.println("Informe seu email: ");
                    String email = null;
                    while (true) {
                        email = scanner.nextLine();
                        if (Auxiliar.validarEmail(email)) {
                            break;
                        }
                        System.out.println("Email inválido, tente novamente.\n");
                    }
                    System.out.println("Informe a sua data de nascimento:(ano-mes-dia)");
                    String dataNascimento = null;
                    LocalDate dataNascimentoFormatada = null;
                    while (true) {
                        dataNascimento = scanner.nextLine();
                        dataNascimentoFormatada = Auxiliar.validarDataNascimento(dataNascimento);
                        if (dataNascimentoFormatada != null) {
                            break;
                        }
                        System.out.println("Data inválida, tente novamente.\n");
                    }
                    System.out.println("Escolha um cargo: \n1 - Membro.\n2 - Gerente.\n");
                    int cargo;
                    while (true) {
                        try {
                            cargo = scanner.nextInt();
                            scanner.nextLine();
                            if (cargo == 1 || cargo == 2) {
                                break;
                            }
                            System.out.println("Opção inválida, tente novamente.\n");
                        } catch (Exception e) {
                            System.out.println("Opção inválida, tente novamente.\n");
                            scanner.nextLine();
                        }
                    }
                    System.out.println("Informe a sua senha: ");
                    String senha = scanner.nextLine();
                    if (pessoaController.existeCpf(cpf)) {
                        System.out.println("Já existe um cadastro com este cpf, efetuie login.\n");                            
                        break;
                    }
                    if (pessoaController.existeEmail(email)) {
                        System.out.println("Já existe um cadastro com este email, efetue login.\n");                            
                        break;
                    }
                    System.out.println("Informe o seu cep: ");
                    String cep = null;
                    while (true) {
                        cep = scanner.nextLine();
                        if (Auxiliar.validarCEP(cep)) {
                            break;
                        }
                        System.out.println("Cep inválido, tente novamente.\n");
                    }
                    System.out.println("Informe o complemento: ");
                    String complemento = scanner.nextLine();
                    Endereco endereco = new Endereco(complemento, cep);
                    Pessoa pessoa = new Pessoa(cpf, nome, dataNascimentoFormatada, senha, email, cargo, endereco);
                    boolean pessoaInserida = pessoaController.inserirPessoa(pessoa);
                    boolean enderecoInserido = enderecoController.inserirEndereco(endereco, cpf);
                    if (pessoaInserida && enderecoInserido) {
                        System.out.println("Cadastro realizado com sucesso.\n");
                    }
                    break;

                case 3:
                    System.out.println("Programa encerrado com sucesso.\n");
                    return null;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Opção inválida.\n");
        }

    }
}

    public static String cadastrarItem(Scanner scanner, Pessoa usuarioAtivo) {
        String nome_estoque;
        String descricao;
        System.out.println("Informe o nome do item que você deseja cadastrar: ");
        nome_estoque = scanner.nextLine();
        System.out.println("Dê a descrição do produto: ");
        descricao = scanner.nextLine();
        int idCadastrado = estoqueController.cadastrar(new Estoque(nome_estoque, descricao));
        if (idCadastrado == -1) {
            return "Erro ao cadastrar item.\n";
        }
        return historicoController.inserir(new Historico(usuarioAtivo.getCpf(), idCadastrado, null, null));
    }
}