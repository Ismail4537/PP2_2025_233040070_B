package Modul05;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Latihan4 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela linggis");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                JLabel label = new JLabel("Label di atas");
                JButton button = new JButton("Tombol di bawah");

                button.addActionListener(e -> {
                    label.setText("Tombol di klik");
                });

                frame.add(label, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);
                frame.add(new JButton("kiri"), BorderLayout.WEST);
                frame.add(new JButton("kanan"), BorderLayout.EAST);
                frame.add(new JButton("center"), BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });

    }
}
