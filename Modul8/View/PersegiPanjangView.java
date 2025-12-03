package Modul8.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PersegiPanjangView extends JFrame {
    JTextField panjangField = new JTextField(10);
    JTextField lebarField = new JTextField(10);
    JLabel hasilLabel = new JLabel("0");
    JButton luasButton = new JButton("Hitung Luas");
    JButton kelilingButton = new JButton("Hitung Keliling");
    JButton resetButton = new JButton("Reset Hasil");
    JPanel mainPanel = new JPanel();

    public PersegiPanjangView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        this.setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(5, 2, 3, 3));
        setTitle("MVC Persegi Panjang");

        mainPanel.add(new JLabel("Panjang:"));
        mainPanel.add(this.panjangField);
        mainPanel.add(new JLabel("Lebar:"));
        mainPanel.add(this.lebarField);
        mainPanel.add(new JLabel("Hasil:"));
        mainPanel.add(this.hasilLabel);
        mainPanel.add(this.luasButton);
        mainPanel.add(this.kelilingButton);
        this.add(this.mainPanel, BorderLayout.CENTER);
        this.add(this.resetButton, BorderLayout.SOUTH);
    }

    public double getPanjang() {
        return Double.parseDouble(panjangField.getText());
    }

    public double getLebar() {
        return Double.parseDouble(lebarField.getText());
    }

    public void setHasil(double hasil) {
        hasilLabel.setText(String.valueOf(hasil));
    }

    public void tampilError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void addLuasListener(ActionListener listener) {
        luasButton.addActionListener(listener);
    }

    public void addKelilingListener(ActionListener listener) {
        kelilingButton.addActionListener(listener);
    }

    public void addResetListener(ActionListener listener) {
        resetButton.addActionListener(listener);
    }
}
