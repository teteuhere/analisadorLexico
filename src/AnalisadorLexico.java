import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnalisadorLexico {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Loop até o usuário digitar "sair"
        while (true) {
            System.out.print("Digite a expressão para análise léxica (ou 'sair' para encerrar): ");
            String entradaUsuario = scanner.nextLine();

            analisadorLexico(entradaUsuario);

            if (entradaUsuario.toLowerCase().equals("sair")) {
                System.out.println("Encerrando o programa...");
                break;  // Encerra o loop externo
            }
        }

        scanner.close();
    }

    private static void analisadorLexico(String entrada) {
        String[] palavrasReservadas = {"a", "yn", "amarth", "a-nath", "a-laita", "ant", "teulu", "thargaen",
                "yn-amarth", "ant-teulu", "a-yn", "ant-a", "ant-amarth", "thargaen-amarth",
                "i", "ant-yn", "teulu-teulu", "a-amarth", "yn-a", "ant-thargaen", "teulu",
                "yn-yn-amarth", "thargaen-ant-teulu", "ant-amarth", "a-a-a", "ant-ant",
                "thargáen-a-amarth", "amarth-a", "ant-thargaen", "a-yn-yn", "i-thargaen",
                "amarth-thargaen", "yn-thargaen", "ensíla", "amarthh", "nidhoel", "nedheth"};

        Map<String, String> operadores = new HashMap<>();
        operadores.put("==", "igual a");
        operadores.put("+", "soma");
        operadores.put("-", "subtração");
        operadores.put("/", "divisão");
        operadores.put("*", "multiplicação");
        operadores.put(">", "maior que");
        operadores.put("<", "menor que");
        operadores.put(">=", "maior ou igual a");
        operadores.put("<=", "menor ou igual a");
        operadores.put("!=", "diferente de");
        operadores.put(",", "operador de ponto");
        operadores.put(".", "operador de ponto");
        operadores.put("\"", "aspas duplas");
        operadores.put("'", "aspas simples");
        operadores.put("`", "aspas simples");
        operadores.put("%", "porcentagem");
        operadores.put("&", "e comercial");
        operadores.put("(", "parêntese aberto");
        operadores.put(")", "parêntese fechado");
        operadores.put("[", "colchete aberto");
        operadores.put("]", "colchete fechado");
        operadores.put("{", "chave aberta");
        operadores.put("}", "chave fechada");
        operadores.put("~", "til");
        operadores.put(";", "ponto e vírgula");
        operadores.put(":", "dois pontos");

        String[] tokens = entrada.split(" ");

        for (String token : tokens) {
            if (token.toLowerCase().equals("sair")) {
                System.out.println("Encerrando o programa...");
                return;
            }

            if (isPalavraReservada(token, palavrasReservadas)) {
                System.out.println("Palavra reservada: " + token);
                for (int i = 1; i <= token.length(); i++) {
                    System.out.println(token.substring(0, i));
                }
            } else if (isNumeroInteiro(token)) {
                System.out.println("Número inteiro: " + token);
            } else if (token.contains(".") && isNumeroReal(token.replace(".", ""))) {
                System.out.println("Número real: " + token);
            } else if (operadores.containsKey(token)) {
                System.out.println("Operador: " + token + " - " + operadores.get(token));
            } else if (isLetraDoAlfabeto(token)) {
                System.out.println("Alfabeto: " + token);
            } else {
                System.out.println("Token não reconhecido: " + token);
            }
        }
    }

    private static boolean isPalavraReservada(String token, String[] palavrasReservadas) {
        for (String palavra : palavrasReservadas) {
            if (token.equals(palavra)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isNumeroInteiro(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isNumeroReal(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isLetraDoAlfabeto(String token) {
        return token.length() == 1 && Character.isLetter(token.charAt(0));
    }
}
