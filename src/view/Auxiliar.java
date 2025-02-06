package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Auxiliar {

    public static String formatarCPF(String cpf) {
        // Tira os pontos
        cpf = (cpf.replaceAll("\\D", ""));

        // coloca um ponto a cada 3 números e - no antepenúltimo
        if (cpf.length() == 11) {
            return (cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11));
        }
        return null;
    }

    public static boolean validarCEP(String cep) {
        // Expressão regular para validar CEP (com ou sem hífen)
        String regex = "^\\d{5}-?\\d{3}$";
        return Pattern.matches(regex, cep);
    }

    public static boolean validarCPF(String cpf) {
        // Tira os pontos
        cpf = (cpf.replaceAll("[^a-zA-Z0-9]", ""));

        // Verifica se tem 11 dígitos       
        if (cpf.length() != 11) {
            return false;
        }

        for (char c : cpf.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public static boolean validarEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(regex, email);
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LocalDate validarDataNascimento(String dataStr) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate idadeMaxima = LocalDate.parse("1925-01-01", formato);

        try {
            LocalDate dataNascimento = LocalDate.parse(dataStr, formato);
            LocalDate hoje = LocalDate.now();
            LocalDate dataMinima = hoje.minusYears(18);
            if (dataNascimento.isBefore(dataMinima) && dataNascimento.isAfter(idadeMaxima)) {
                return dataNascimento;
            }
            return null;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String menuPrincipal(int cargo) {
        switch (cargo) {
            case 1:
            return "Menu:\n1 - Listar todos os itens.\n2 - Buscar item.\n3 - Exibir histórico.\n0 - Encerrar o programa.";

            case 2:
                return "Menu:\n1 - Cadastrar item no estoque.\n2 - Adicionar item.\n3 - Retirar item.\n4 - Listar todos os itens.\n5 - Buscar item.\n6 - Exibir histórico.\n0 - Encerrar programa.\n";
            
            default:
                return "";
        }
    }
}
