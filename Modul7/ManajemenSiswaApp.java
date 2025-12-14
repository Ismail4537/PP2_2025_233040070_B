package Modul7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManajemenSiswaApp extends JFrame {
    JTextField namaField;
    JTextField nilaiField;
    JComboBox<String> matpelComboBox;
    JTable dataTable;
    DefaultTableModel tableModel;
    JTabbedPane tabbedPane;

    public ManajemenSiswaApp() {
        setTitle("Aplikasi Manajemen nilai siswa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel inputPanel = createInputPanel();
        tabbedPane.addTab("Input Data Siswa", inputPanel);

        JPanel tablePanel = createTablePanel();
        tabbedPane.addTab("Daftar Nilai", tablePanel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ManajemenSiswaApp app = new ManajemenSiswaApp();
            app.setVisible(true);
        });
    }

    JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nama Siswa:"));
        namaField = new JTextField();
        panel.add(namaField);

        panel.add(new JLabel("Mata Pelajaran:"));
        String[] matpel = { "Matematika", "Bahasa Indonesia", "Bahasa Inggris",
                "IPA", "IPS" };
        matpelComboBox = new JComboBox<>(matpel);
        panel.add(matpelComboBox);

        panel.add(new JLabel("Nilai (0-100):"));
        nilaiField = new JTextField();
        panel.add(nilaiField);

        JButton submitButton = new JButton("Submit");
        panel.add(new JLabel());
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        return panel;
    }

    JPanel createTablePanel() {
        JPanel panel = new JPanel();

        String[] kolom = { "Nama Siswa", "Mata Pelajaran", "Nilai", "Index" };
        tableModel = new DefaultTableModel(kolom, 0);
        dataTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    void prosesSimpan() {
        String nama = namaField.getText();
        String matpel = (String) matpelComboBox.getSelectedItem();
        String nilaiStr = nilaiField.getText();

        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama siswa tidak boleh kosong!",
                    "ErrorValidasi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(nilaiStr);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 hingga 100!",
                        "ErrorValidasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!",
                    "ErrorValidasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String index;
        if (nilai >= 85) {
            index = "A";
        } else if (nilai >= 70) {
            index = "B";
        } else if (nilai >= 55) {
            index = "C";
        } else if (nilai >= 40) {
            index = "D";
        } else {
            index = "E";
        }

        Object[] baris = { nama, matpel, nilai, index };
        tableModel.addRow(baris);

        JOptionPane.showMessageDialog(this, "Data siswa berhasil disimpan!",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
        namaField.setText("");
        nilaiField.setText("");
        matpelComboBox.setSelectedIndex(0);
        tabbedPane.setSelectedIndex(1);
    }

}
