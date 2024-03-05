import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calculate(input));
    }

    public static String calculate(String stroka) {
        String[] parts = stroka.split(" ");
        String operator1 = parts[0];
        String znak = parts[1];
        String operator2 = parts[2];
        if (znak.equals("+")) {
            char kavichki = '\"';
            if (operator1.charAt(0) != kavichki && operator1.charAt(operator1.length()) != kavichki) {
                throw new IllegalArgumentException("Первый оператор должен быть строкой");
            }
            return sumString(operator1, operator2);
        } else if (znak.equals("-")) {
            char kavichki = '\"';
            if (operator1.charAt(0) != kavichki && operator1.charAt(operator1.length()) != kavichki) {
                throw new IllegalArgumentException("Первый оператор должен быть строкой");
            }
            return minusString(operator1, operator2);
        } else if (znak.equals("*")) {
            if (!operator2.matches("[1-9|10]")) {
                throw new IllegalArgumentException("Числа не должны быть меньше 1 и превышать 10");
            }
            return multiplyString(operator1, Integer.parseInt(operator2));
        } else if (znak.equals("/")) {
            if (!operator2.matches("[1-9|10]")) {
                throw new IllegalArgumentException("Числа не должны быть меньше 1 и превышать 10");
            }
            return divideString(operator1, Integer.parseInt(operator2));
        } else {
            throw new IllegalArgumentException("Вы ввели некорректный оператор вычисления");
        }

    }

    public static String sumString(String a, String b) {
        String newString = a.substring(1, a.length() - 1) + b.substring(1, b.length() - 1);
        return "\"" + newString + "\"";

    }

    public static String minusString(String a, String b) {
        return a.replace(b, "");

    }

    public static String multiplyString(String a, int b) {
        String newString = a.substring(1, a.length() - 1);
        return "\"" + newString.repeat(b) + "\"";              // сразу возвращает результат
    }

    public static String divideString(String a, int b) {
        int length = a.length() / b;        // новая длинна строки
        String newString = a.substring(0, length);
        return newString + "\"";
    }
}
