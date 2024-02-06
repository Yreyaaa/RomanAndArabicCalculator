package org.kata.yreya;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    // Словарь для конвертирования римских чисел в арабские
    final static HashMap<Character, Integer> romanMap = new HashMap<>(Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100));

    // Словарь для конвертирования арабских чисел в римские
    final static TreeMap<Integer, String> arabianMap = new TreeMap<>(Map.of(100, "C", 90, "XC", 50, "L", 40, "XL", 10, "X", 9, "IX", 5, "V", 4, "IV", 1, "I"));

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));

    }

    public static String calc(String input) throws Exception {

        // Удаляем лишние пробелы и разделяем ввод на отдельные элементы по пробелу
        input = input.trim().replaceAll("\\s+", " ").toUpperCase();
        String[] parsedInput = input.split(" ");


        // Проверка ввода на валидность
        if (!inputIsValid(parsedInput)) {
            throw new Exception("Неверный формат ввода, пример корректных входных данных: 3 + 6 или X * IV");
        }

        // Получаем аргументы для вычисления в арабском формате
        int firstArgument = Character.isDigit(parsedInput[0].charAt(0)) ? Integer.parseInt(parsedInput[0]) : romanToArabian(parsedInput[0]);
        int secondArgument = Character.isDigit(parsedInput[2].charAt(0)) ? Integer.parseInt(parsedInput[2]) : romanToArabian(parsedInput[2]);

        char operator = parsedInput[1].charAt(0);

        int result = switch (operator) {
            case '+' -> firstArgument + secondArgument;
            case '-' -> firstArgument - secondArgument;
            case '*' -> firstArgument * secondArgument;
            case '/' -> firstArgument / secondArgument;
            default -> -1;
        };

        // Возвращаем результат в соответстви с форматом ввода
        if (Character.isDigit(parsedInput[0].charAt(0))) {
            return String.valueOf(result);
        } else if (result >= 1) {
            return arabicToRoman(result);
        } else {
            throw new Exception("C римскими числами полученно значение выражения меньше 1");
        }
    }

    private static boolean inputIsValid(String[] parsedInput) {


        if (parsedInput.length == 3) {
            if ((parsedInput[0].chars().allMatch(Character::isDigit)) && (parsedInput[2].chars().allMatch(Character::isDigit))) {
                int x = Integer.parseInt(parsedInput[0]);
                int y = Integer.parseInt(parsedInput[2]);
                if (!((x >= 0 && x <= 10) && (y >= 0 && y <= 10))) {
                    return false;
                }
                return parsedInput[1].matches("[+-/*]");
            } else if (parsedInput[0].matches("[IVXLC]+") && parsedInput[2].matches("[IVXLC]+")) {
                int x = romanToArabian(parsedInput[0]);
                int y = romanToArabian(parsedInput[2]);
                if (!((x >= 0 && x <= 10) && (y >= 0 && y <= 10))) {
                    return false;
                }
                return parsedInput[1].matches("[+-/*]");
            }
        }
        return false;
    }

    private static int romanToArabian(String roman) {

        //Конвентируем римское чисто в арабское
        int arabian;
        int result = romanMap.get(roman.charAt(roman.length() - 1));
        for (int i = roman.length() - 2; i >= 0; i--) {
            arabian = romanMap.get(roman.charAt(i));
            if (arabian < romanMap.get(roman.charAt(i + 1))) {
                result = result - arabian;
            } else {
                result = result + arabian;
            }
        }
        return result;
    }

    private static String arabicToRoman(int arabian) {

        //Конвентируем арабское чисто в римское
        StringBuilder roman = new StringBuilder();
        while (arabian != 0) {
            roman.append(arabianMap.get(arabianMap.floorKey(arabian)));
            arabian = arabian - arabianMap.floorKey(arabian);
        }
        return roman.toString();
    }
}