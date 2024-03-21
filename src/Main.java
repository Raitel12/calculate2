import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        //Ввод строки пользователем
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //Разбиваем строку по пробелу
        String[] parts = input.split(" ");

        //Проверяем корректность выражения
        if (parts.length == 1) {
            throw new IllegalArgumentException("Введенное выражение должно быть вида \"а\" + \"b\"");
        }

        //Объявляем переменные
        String operator1;
        String znak = null;
        String operator2;

        //Проверка на наличие пробелов в строках

        //если ввели строки без пробелов
        if (parts.length == 3) {
            operator1 = parts[0];
            znak = parts[1];
            operator2 = parts[2];
            System.out.println(calculate(operator1, operator2, znak));
        }
        //если ввели строки с пробелами
        else {
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].contains("+") ||
                        parts[i].contains("-") ||
                        parts[i].contains("*") ||
                        parts[i].contains("/")) {
                    znak = parts[i];
                    if (znak.contains("+")) {
                        parts = input.split("\\+");
                    } else if (znak.contains("-")) {
                        parts = input.split("-");
                    } else if (znak.contains("/")) {
                        parts = input.split("/");
                    } else if (znak.contains("*")) {
                        parts = input.split("\\*");
                    }


                    int newLengthA = parts[0].length() - parts[0].trim().length();
                    int newLengthB = parts[1].length() - parts[1].trim().length();
                    if (newLengthA != 1 || newLengthB != 1) {
                        throw new IllegalArgumentException("Введенное выражение должно быть вида \"а\" + \"b\"");
                    }
                    operator1 = parts[0].trim();
                    operator2 = parts[1].trim();
                    System.out.println(calculate(operator1, operator2, znak));
                }
            }
        }
        //если ввели некорректный знак
        if (znak == null) {
            throw new IllegalArgumentException("Вы ввели некорректный оператор вычисления");
        }
    }

    public static String calculate(String operator1, String operator2, String znak) {
        if (znak.contains("+")) {
            if (!operator1.startsWith("\"") || !operator1.endsWith("\"")) {
                throw new IllegalArgumentException("Первый оператор должен быть строкой");
            }
            return sumString(operator1, operator2);
        } else if (znak.contains("-")) {
            if (!operator1.startsWith("\"") || !operator1.endsWith("\"")) {
                throw new IllegalArgumentException("Первый оператор должен быть строкой");
            }
            return minusString(operator1, operator2);
        } else if (znak.contains("*")) {
            if (Integer.parseInt(operator2) < 1 || Integer.parseInt(operator2) > 10) {
                throw new IllegalArgumentException("Числа не должны быть меньше 1 и превышать 10");
            }
            return multiplyString(operator1, Integer.parseInt(operator2));
        } else if (znak.contains("/")) {
            if (Integer.parseInt(operator2) < 1 || Integer.parseInt(operator2) > 10) {
                throw new IllegalArgumentException("Числа не должны быть меньше 1 и превышать 10");
            }
            return divideString(operator1, Integer.parseInt(operator2));
        } else {
            throw new IllegalArgumentException("Вы ввели некорректный оператор вычисления");
        }
    }

    public static String sumString(String a, String b) {
        String newA = a.substring(1, a.length() - 1);
        String newB = b.substring(1, b.length() - 1);
        String newString = newA + newB;
        return "\"" + newString + "\"";

    }


    public static String minusString(String a, String b) {
        String newA = a.substring(1, a.length() - 1);
        String newB = b.substring(1, b.length() - 1);
        return "\"" + newA.replace(newB, "") + "\"";
    }

    public static String multiplyString(String a, int b) {
        String newA = a.substring(1, a.length() - 1);
        String result = newA.repeat(b);
        if (result.length() > 40) {
            String newResult = result.substring(0, 40);
            return "\"" + newResult + "..." + "\"";
        } else {
            return "\"" + result + "\"";
        }
    }

    // "Example!!!" / 3
    // "123456789123456789123456789123456789123456789" / 1
    public static String divideString(String a, int b) {
        int length = a.length() / b;  // =3
        String result = a.substring(0, length); // Exa
        if (result.length() > 40) {
            String newResult = result.substring(0, 40);
            return newResult + "..." + "\"";
        } else {
            return result + "\"";
        }
    }
}