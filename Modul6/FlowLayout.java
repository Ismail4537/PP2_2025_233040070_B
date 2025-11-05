package Modul6;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FlowLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jendela Ngawi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        JPanel panel = new JPanel();
        panel.add(new JButton("Tombol 1"));
        panel.add(new JButton("Tombol 2"));
        panel.add(new JButton("Tombol tiga"));
        panel.add(new JButton("Tombol empat panjang"));
        panel.add(new JButton("Tombol 5"));
        frame.add(panel);
        frame.setVisible(true);
    }
}
