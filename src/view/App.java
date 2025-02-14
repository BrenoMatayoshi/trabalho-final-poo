package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import controller.ClienteController;
import controller.EnderecoController;
import controller.EstoqueController;
import controller.HistoricoController;
import controller.UsuarioController;
import model.Cliente;
import model.Endereco;
import model.Estoque;
import model.Historico;
import model.Usuario;

public class App {
    static UsuarioController pessoaController = new UsuarioController();
    static EnderecoController enderecoController = new EnderecoController();
    static EstoqueController estoqueController = new EstoqueController();
    static HistoricoController historicoController = new HistoricoController();
    static ClienteController clienteController = new ClienteController();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioAtivo = menuLogin(scanner);
        Estoque item;
        Cliente empresa;
        if (usuarioAtivo == null) {
            return;
        }
        while (true) {
            System.out.println("Usuário: " + usuarioAtivo.getNomePessoa() + ".\nNível de permissão: " + Auxiliar.permissao(usuarioAtivo.getIdCargo()) + ".\n");
            System.out.println("Menu:\n1 - Cadastrar item no estoque.\n2 - Adicionar item.\n3 - Retirar item.\n4 - Listar todos os itens.\n5 - Buscar item.\n6 - Exibir histórico.\n7 - Cadastrar empresa.\n0 - Encerrar programa.\n");
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                    if (permissao(usuarioAtivo)) {
                        break;
                    }
                        System.out.println(cadastrarItem(scanner, usuarioAtivo));
                        Auxiliar.tempoELimparTela();
                        break;
                
                    case 2:
                        if (permissao(usuarioAtivo)) {
                            break;
                        }
                        item = selecionarItem(scanner);
                        if (item == null) {
                            break;
                        }
                        empresa = selecionarEmpresa(scanner);
                        if (empresa == null) {
                            break;
                        }
                        try {
                            System.out.println("Informe a quantidade que você deseja adicionar: ");
                            int quantidade = scanner.nextInt();
                            scanner.nextLine();
                            if (quantidade < 0 || quantidade == 0) {
                                System.out.println("Quantidade inválida.\n");
                                break;
                            }
                            System.out.println("Informe a preço de compra: ");
                            float preco = scanner.nextFloat();
                            scanner.nextLine();
                            if (historicoController.inserir(new Historico(usuarioAtivo.getCpf(), item.getId_estoque(), quantidade, preco, empresa.getId_cliente())).equals("Sucesso.\n")) {
                                item.setUpdate_estoque(quantidade);
                                estoqueController.update(item);
                                System.out.println("Adição bem sucedida.\n");
                                Auxiliar.tempoELimparTela();
                            }
                        } catch (Exception e) {
                            System.out.println("Quantidade inválida.\n");
                            scanner.nextLine();
                        }
                        break;
                
                    case 3:
                    if (permissao(usuarioAtivo)) {
                        break;
                    }
                        item = selecionarItem(scanner);
                        if (item == null) {
                            break;
                        }
                        empresa = selecionarEmpresa(scanner);
                        if (empresa == null) {
                            break;
                        }
                        try {
                            System.out.println("Informe a quantidade que você deseja remover: ");
                            int quantidade = scanner.nextInt();
                            scanner.nextLine();
                            if (quantidade > item.getQuantidade_estoque() || quantidade == 0) {
                                System.out.println("Quantidade insuficiente.\n");
                                break;
                            }
                            System.out.println("Informe a preço de venda: ");
                            float preco = scanner.nextFloat();
                            scanner.nextLine();
                            if (historicoController.inserir(new Historico(usuarioAtivo.getCpf(), item.getId_estoque(), -Math.abs(quantidade), preco, empresa.getId_cliente())).equals("Sucesso.\n")) {
                                item.setUpdate_estoque(-Math.abs(quantidade));
                                estoqueController.update(item);
                                System.out.println("Retirada bem sucedida.\n");
                                Auxiliar.tempoELimparTela();
                            }
                        } catch (Exception e) {
                            System.out.println("Quantidade inválida.\n");
                            scanner.nextLine();
                        }
                        break;
                
                    case 4:
                        listarItens();
                        break;
                
                    case 5:
                        buscarItem(scanner);
                        break;
                
                    case 6:
                        listarHistorico();
                        break;
                
                    case 7:
                        cadastrarEmpresa(scanner);
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
                System.out.println(e);
                System.out.println("Opção inválida.\n");
            }
        }
    }

    public static boolean permissao(Usuario usuarioAtivo) {
        if (Auxiliar.verificarPermissao(usuarioAtivo.getIdCargo(), "gerente")) {
            System.out.println("Permissão insuficiente.\n");
            return true;
        }
        return false;
    }

    public static Usuario menuLogin(Scanner scanner) {
        while (true) {
            System.out.println("1 - Login.\n2 - Registrar.\n3 - Sair.\n");
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        return realizarLogin(scanner);

                    case 2:
                        realizarRegistro(scanner);
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

    public static Usuario realizarLogin(Scanner scanner) {
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
            Usuario usuario = pessoaController.login(login, senha);
            if (usuario != null) {
                System.out.println("Login bem sucedido.\n");
                Auxiliar.tempoELimparTela();
                return usuario;
            }
            System.out.println("Login ou senha inválidos, tente novamente.\n");
        }
    }

    public static void realizarRegistro(Scanner scanner) {
        Auxiliar.limparTela();
        System.out.println("Registrar:\nInforme seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Informe seu cpf: ");
        String cpf = cadastrarCPF(scanner);

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
        LocalDate dataNascimentoFormatada = data(scanner);
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
            return;
        }
        if (pessoaController.existeEmail(email)) {
            System.out.println("Já existe um cadastro com este email, efetue login.\n");                            
            return;
        }
        Endereco endereco = cadastrarEndereco(scanner);
        Usuario pessoa = new Usuario(cpf, nome, dataNascimentoFormatada, senha, email, cargo, endereco);
        boolean pessoaInserida = pessoaController.inserirPessoa(pessoa);
        boolean enderecoInserido = enderecoController.inserirEndereco(endereco, cpf);
        if (!enderecoInserido) {
            System.out.println("Endereço mal");
        }
        if (!pessoaInserida) {
            System.out.println("pessoa mal");
        }
        if (pessoaInserida && enderecoInserido) {
            System.out.println("Cadastro realizado com sucesso.\n");
            Auxiliar.tempoELimparTela();
        }
    }

    public static String cadastrarCPF(Scanner scanner) {
        String cpf;
        while (true) {
            cpf = scanner.nextLine();
            if (Auxiliar.validarCPF(cpf)) {
                cpf = Auxiliar.formatarCPF(cpf);
                return cpf;
            }
            System.out.println("Cpf inválido, tente novamente.\n");
        }
    }

    public static String cadastrarItem(Scanner scanner, Usuario usuarioAtivo) {
        String nome_estoque;
        String descricao;
        System.out.println("Informe o nome do item que você deseja cadastrar: ");
        nome_estoque = scanner.nextLine();
        System.out.println("Dê a descrição do produto: ");
        descricao = scanner.nextLine();
        int idCadastrado = estoqueController.inserir(new Estoque(nome_estoque, descricao, 0));
        if (idCadastrado == -1) {
            return "Erro ao cadastrar item.\n";
        }
        return historicoController.inserir(new Historico(usuarioAtivo.getCpf(), idCadastrado, 0, 0, 0));
    }

    public static Endereco cadastrarEndereco(Scanner scanner) {
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
        return new Endereco(complemento, cep);
    }

    public static boolean listarItens() {
        ArrayList<Estoque> arrayList = estoqueController.getItens();
        Iterator<Estoque> iterator = arrayList.iterator();
        if (arrayList.size() == 0) {
            System.out.println("Não há itens cadastrados.\n");
            return false;
        }
        while (iterator.hasNext()) {
            Estoque elemento = iterator.next();
            Auxiliar.imprimir(elemento);
        }
        return true;
    }

    public static void listarHistorico() {
        ArrayList<Historico> arrayList = historicoController.getHistorico();
        Iterator<Historico> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Historico elemento = iterator.next();
            Auxiliar.imprimir(elemento);
        }
    }

    public static Estoque selecionarItem(Scanner scanner) {
        if (!listarItens()) {
            return null;
        }
        System.out.println("Selecione o id do item: ");
        try {
            int idEscolhido = scanner.nextInt();
            scanner.nextLine();
            if (idEscolhido < 1 || idEscolhido > estoqueController.quantidadeItens()) {
                return estoqueController.getEstoque(idEscolhido);
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Opção inválida, tente novamente.\n");
        }
        System.out.println("Item não encontrado.\n");
        return null;
    }

    public static void buscarItem(Scanner scanner) {
        System.out.println("Informe o nome ou ID do item: ");
        String input = scanner.nextLine();
        try {
            int id = Integer.parseInt(input);
            Estoque item = estoqueController.getEstoque(id);
            if (item != null) {
                Auxiliar.imprimir(item);
            } else {
                System.out.println("Item não encontrado.\n");
            }
        } catch (NumberFormatException e) {
            ArrayList<Estoque> itens = estoqueController.getItens();
            if (!itens.isEmpty()) {
                for (Estoque item : itens) {
                    Auxiliar.imprimir(item);
                }
            } else {
                System.out.println("Item não encontrado.\n");
            }
        }
    }

    public static LocalDate data(Scanner scanner) {
        String data = null;
        LocalDate dataFormatada = null;
        while (true) {
            data = scanner.nextLine();
            dataFormatada = Auxiliar.validarDataNascimento(data);
            if (dataFormatada != null) {
                return dataFormatada;
            }
            System.out.println("Data inválida, tente novamente.\n");
        }
    }

    public static void cadastrarEmpresa(Scanner scanner) {
        String cnpj;
        System.out.println("Informe o nome da empresa: ");
        String nomeEmpresa = scanner.nextLine();
        System.out.println("Informe o CNPJ da empresa: ");
        while (true) {
            cnpj = scanner.nextLine();
            if (Auxiliar.validarCNPJ(cnpj)) {
                cnpj = Auxiliar.formatarCNPJ(cnpj);
                break;
            }
        }
        System.out.println("Informe o nome do responsável: ");
        String nomeResponsavel = scanner.nextLine();
        System.out.println("Informe o CPF do responsável: ");
        String cpf = cadastrarCPF(scanner);
        Cliente cliente = new Cliente(cpf, nomeResponsavel, nomeEmpresa, cnpj);
        if (clienteController.criarCliente(cliente)) {
            System.out.println("Cadastro realizado com sucesso.\n");
        }
    }

    public static boolean listarEmpresas() {
        ArrayList<Cliente> empresas = clienteController.getClientes();
        Iterator<Cliente> iterator = empresas.iterator();
        if (empresas.size() == 0) {
            System.out.println("Não há empresas cadastradas.\n");
            return false;
        }
        while (iterator.hasNext()) {
            Cliente elemento = iterator.next();
            Auxiliar.imprimir(elemento);
        }
        return true;
    }

    public static Cliente selecionarEmpresa(Scanner scanner) {
        if (!listarEmpresas()) {
            return null;
        }
        System.out.println("Selecione o id da empresa: ");
        try {
            int idEscolhido = scanner.nextInt();
            scanner.nextLine();
            if (idEscolhido > 0 || idEscolhido < clienteController.getClientes().size()) {
                return clienteController.getCliente(idEscolhido);
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Opção inválida, tente novamente.\n");
        }
        System.out.println("Empresa não encontrada.\n");
        return null;
    }
}