package Modul6;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class BordeLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jendela sungut lele");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        frame.add(new JButton("NORTH"), BorderLayout.NORTH);
        frame.add(new JButton("WEST"), BorderLayout.WEST);
        frame.add(new JButton("CENTER"), BorderLayout.CENTER);
        frame.add(new JButton("SOUTH"), BorderLayout.SOUTH);
        frame.add(new JButton("EAST"), BorderLayout.EAST);

        frame.setVisible(true);
    }
}
