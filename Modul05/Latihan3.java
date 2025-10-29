package Modul05;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela linggis");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new FlowLayout());

                JLabel label = new JLabel("Ambasing");
                JButton button = new JButton("Klik saya");

                button.addActionListener(e -> {
                    label.setText("Ambatukam");
                });

                frame.add(label);
                frame.add(button);

                frame.setVisible(true);
            }
        });

    }
}
