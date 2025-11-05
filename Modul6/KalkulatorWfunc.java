package Modul6;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KalkulatorWfunc {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout(5, 5));

        JTextField display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        frame.add(panel, BorderLayout.CENTER);

        JButton[] buttons = {
                new JButton("7"), new JButton("8"), new JButton("9"), new JButton("/"),
                new JButton("4"), new JButton("5"), new JButton("6"), new JButton("*"),
                new JButton("1"), new JButton("2"), new JButton("3"), new JButton("-"),
                new JButton("0"), new JButton("."), new JButton("="), new JButton("+")
        };

        ActionListener addNum = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String currentText = display.getText();
                String buttonText = ((JButton) e.getSource()).getText();
                display.setText(currentText + buttonText);
            }
        };

        ActionListener calculate = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double[] numbers = new double[2];
                    String operator = display.getText().replaceAll("[0-9]", "");
                    numbers[0] = Integer.parseInt(display.getText().split("[+\\-*/]")[0]);
                    numbers[1] = Integer.parseInt(display.getText().split("[+\\-*/]")[1]);
                    switch (operator) {
                        case "+":
                            display.setText(String.valueOf(numbers[0] + numbers[1]));
                            break;
                        case "-":
                            display.setText(String.valueOf(numbers[0] - numbers[1]));
                            break;
                        case "*":
                            display.setText(String.valueOf(numbers[0] * numbers[1]));
                            break;
                        case "/":
                            display.setText(String.valueOf(numbers[0] / numbers[1]));
                            break;
                    }
                } catch (Exception ex) {
                    display.setText("");
                }
            }
        };

        for (JButton button : buttons) {
            if (button.getText().equals("=")) {
                button.addActionListener(calculate);
            } else {
                button.addActionListener(addNum);
            }
            panel.add(button);
        }
        frame.setVisible(true);
    }
}
