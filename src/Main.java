import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calculate(input));
    }

    public static String calculate(String stroka) {
        String[] parts = stroka.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Некорректное выражение");
        }
        String operator1 = parts[0];
        String znak = parts[1];
        String operator2 = parts[2];
        if (!operator1.startsWith("\"") || !operator1.endsWith("\"")) {
            throw new IllegalArgumentException("Первый оператор должен быть строкой");
        }
        if (znak.equals("+")) {
            return sumString(operator1, operator2);
        } else if (znak.equals("-")) {
            return minusString(operator1, operator2);
        } else if (znak.equals("*")) {
            return multiplyString(operator1, Integer.parseInt(operator2));
        } else if (znak.equals("/")) {
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
        String newB = b.substring(1, b.length() - 1);
        return a.replace(newB, "");

    }

    public static String multiplyString(String a, int b) {
        if (b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа не должны быть меньше 1 и превышать 10");
        }
        String newString = a.substring(1, a.length() - 1);
        String result = newString.repeat(b);
        if (result.length() > 40) {
            String result2 = result.substring(0, 40);
            return "\"" + result2 + "..." + "\"";
        }
        return "\"" + result + "\"";
    }

    public static String divideString(String a, int b) {
        if (b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа не должны быть меньше 1 и превышать 10");
        }
        String newString = a.substring(1, a.length() - 1);
        int newLength = newString.length() / b;
        String result = newString.substring(0, newLength);
        if (result.length() > 40) {
            String result2 = result.substring(0, 40);
            return "\"" + result2 + "..." + "\"";
        }
        return "\"" + result + "\"";
    }
}
