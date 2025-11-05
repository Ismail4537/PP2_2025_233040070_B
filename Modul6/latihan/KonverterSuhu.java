package Modul6.latihan;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KonverterSuhu {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Susuhu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel lab1 = new JLabel("Celcius");
        JTextField celcius = new JTextField();
        JLabel lab2 = new JLabel("Fahrenheit");
        JLabel hasil = new JLabel("0");
        JButton konvert = new JButton("Konvert");

        ActionListener konverte = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double c = Double.parseDouble(celcius.getText());
                    double f = (c * 9 / 5) + 32;
                    hasil.setText(String.valueOf(f));
                } catch (NumberFormatException ex) {
                    hasil.setText("Input Invalid");
                }
            }
        };

        konvert.addActionListener(konverte);
        frame.add(lab1);
        frame.add(celcius);
        frame.add(lab2);
        frame.add(hasil);
        frame.add(konvert);
        frame.setVisible(true);
    }
}
