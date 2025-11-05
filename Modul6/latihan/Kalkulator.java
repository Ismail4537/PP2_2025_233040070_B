package Modul6.latihan;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Kalkulator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Kalkulator Sederhana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout(5, 5));

        JTextField display = new JTextField();
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        JPanel panelNum = new JPanel();
        panelNum.setLayout(new GridLayout(4, 4, 5, 5));
        frame.add(panelNum, BorderLayout.CENTER);

        JButton[] buttons = {
                new JButton("7"), new JButton("8"), new JButton("9"), new JButton("/"),
                new JButton("4"), new JButton("5"), new JButton("6"), new JButton("*"),
                new JButton("1"), new JButton("2"), new JButton("3"), new JButton("-"),
                new JButton("0"), new JButton("."), new JButton("="), new JButton("+")
        };

        for (JButton button : buttons) {
            panelNum.add(button);
        }
        frame.setVisible(true);
    }
}
