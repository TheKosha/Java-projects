package com.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingCalculator {
    private int number1 = 0;
    private int number2 = 0;
    private char operation = ' ';
    private boolean isNumber1 = true;
    private JTextArea display;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingCalculator().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        display = new JTextArea(4, 20);
        display.setFont(new Font("Arial", Font.BOLD, 20));
        display.setEditable(false);
        frame.add(new JScrollPane(display), BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "/", "C", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(new ButtonListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            try {
                if (command.equals("=")) {
                    if (operation != ' ' && !isNumber1) {
                        int result = CalculatorLogic.calculate(number1, number2, operation);
                        display.setText(String.valueOf(result));
                        number1 = result;
                        number2 = 0;
                        operation = ' ';
                        isNumber1 = true;
                    }
                } else if (command.equals("C")) { 
                    number1 = 0;
                    number2 = 0;
                    operation = ' ';
                    isNumber1 = true;
                    display.setText("");
                } else if ("+-*/".contains(command)) {
                    if (!isNumber1) {
                        number1 = CalculatorLogic.calculate(number1, number2, operation);
                        display.setText(String.valueOf(number1));
                        number2 = 0;
                    }
                    operation = command.charAt(0);
                    isNumber1 = false;
                } else {
                    int digit = Integer.parseInt(command);
                    if (isNumber1) {
                        number1 = number1 * 10 + digit;
                        display.setText(String.valueOf(number1));
                    } else {
                        number2 = number2 * 10 + digit;
                        display.setText(String.valueOf(number2));
                    }
                }
            } catch (ArithmeticException ex) {
                display.setText(ex.getMessage());
            } catch (IllegalArgumentException ex) {
                display.setText(ex.getMessage());
            }
        }
    }
}