package org.kata.yreya;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    static HashMap<Character, Integer> romanMap = new HashMap<>();
    static TreeMap<Integer, String> arabianMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        // Словарь для конвертирования римских чисел в арабские
        romanMap.putAll(Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100));

        // Словарь для конвертирования арабских чисел в римские
        arabianMap.putAll(Map.of(100, "C", 90, "XC", 50, "L", 40, "XL", 10, "X", 9, "IX", 5, "V", 4, "IV", 1, "I"));

        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.nextLine()));
    }

    public static String calc(String input) throws Exception {
        // Разделяем ввод на отдельные элементы по пробелу
        String[] parsedInput = input.split(" ");

        //Проверка на валидность ввода
        if (!inputIsValid(parsedInput)) {
            throw new Exception("Неверный формат ввода, пример корректных входных данных: 3 + 6 или X * IV");
        }

        //Получаем аргументы для вычисления в арабском формате
        int firstArgument = Character.isDigit(parsedInput[0].charAt(0)) ? Integer.parseInt(parsedInput[0]) : romanToArabian(parsedInput[0]);
        int secondArgument = Character.isDigit(parsedInput[2].charAt(0)) ? Integer.parseInt(parsedInput[2]) : romanToArabian(parsedInput[2]);

        char operator = parsedInput[1].charAt(0);

        int result = -1;
        switch (operator) {
            case '+':
                result = firstArgument + secondArgument;
                break;
            case '-':
                result = firstArgument - secondArgument;
                break;
            case '*':
                result = firstArgument * secondArgument;
                break;
            case '/':
                result = firstArgument / secondArgument;
                break;
        }

        //Возвращаем результат в соответстви с форматом ввода
        if (Character.isDigit(parsedInput[0].charAt(0))) {
            return String.valueOf(result);
        } else if (result >= 1) {
            return arabicToRoman(result);
        } else {
            throw new Exception("Полученно значение выражения с римскими числами меньше 1");
        }
    }


    private static boolean inputIsValid(String[] parsedInput) {
        if (parsedInput.length == 3) {
            if (Character.isDigit(parsedInput[0].charAt(0)) && Character.isDigit(parsedInput[2].charAt(0))) {
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

    public static int romanToArabian(String roman) {
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
        StringBuilder roman = new StringBuilder();
        while (arabian != 0) {
            roman.append(arabianMap.get(arabianMap.floorKey(arabian)));
            arabian = arabian - arabianMap.floorKey(arabian);
        }
        return roman.toString();
    }
}