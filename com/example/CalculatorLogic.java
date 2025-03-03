package com.example;

public class CalculatorLogic {
    public static int calculate(int number1, int number2, char operation) {
        int result = 0;
        switch (operation) {
            case '+':
                result = number1 + number2;
                break;
            case '-':
                result = number1 - number2;
                break;
            case '*':
                result = number1 * number2;
                break;
            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    throw new ArithmeticException("Ошибка: деление на ноль!");
                }
                break;
            default:
                throw new IllegalArgumentException("Неверная операция!");
        }
        return result;
    }
}