package Modul6;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GridLasyout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jendela medan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        frame.setLayout(new GridLayout(3, 2, 5, 5));

        frame.add(new JLabel("Labelo una"));
        frame.add(new JTextField("Tu Tu"));
        frame.add(new JLabel("Labelo dos"));
        frame.add(new JPasswordField());
        frame.add(new JButton("Login"));
        frame.add(new JButton("Batal"));

        frame.setVisible(true);
    }
}
