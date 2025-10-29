package Modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Tugas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Jendela linggis");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setLayout(new BorderLayout());

                JLabel label = new JLabel("Label di atas");
                JButton buttonSouth = new JButton("Tombol di bawah");
                JButton buttonWest = new JButton("west");
                JButton buttonEast = new JButton("east");
                JButton buttonCenter = new JButton("center");

                buttonSouth.addActionListener(e -> {
                    label.setText("Tombol south di klik");
                });
                buttonWest.addActionListener(e -> {
                    label.setText("Tombol west di klik");
                });
                buttonEast.addActionListener(e -> {
                    label.setText("Tombol east di klik");
                });
                buttonCenter.addActionListener(e -> {
                    label.setText("Tombol center di klik");
                });

                frame.add(label, BorderLayout.NORTH);
                frame.add(buttonSouth, BorderLayout.SOUTH);
                frame.add(buttonWest, BorderLayout.WEST);
                frame.add(buttonEast, BorderLayout.EAST);
                frame.add(buttonCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });

    }
}
