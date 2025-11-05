package Modul6;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ActListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jendela Ambatron");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Tekan tombol ini");
        JButton button = new JButton("Klik Aku");

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("ÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆ");
            }
        };

        button.addActionListener(listener);
        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
    }
}
