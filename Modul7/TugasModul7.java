package Modul7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TugasModul7 extends JFrame {
    JTextField namaField;
    JTextField nilaiField;
    JComboBox<String> matpelComboBox;
    JTable dataTable;
    DefaultTableModel tableModel;
    JTabbedPane tabbedPane;

    public TugasModul7() {
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
            TugasModul7 app = new TugasModul7();
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
        String[] matpel = { "Matematika", "Bahasa Indonesia", "Bahasa Inggris", "IPA", "IPS" };
        matpelComboBox = new JComboBox<>(matpel);
        panel.add(matpelComboBox);

        panel.add(new JLabel("Nilai (0-100):"));
        nilaiField = new JTextField();
        panel.add(nilaiField);

        JButton resetButton = new JButton("Reset");
        JButton submitButton = new JButton("Submit");
        panel.add(resetButton);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesSimpan();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesReset();
            }
        });
        return panel;
    }

    JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JButton deleteButton = new JButton("Delete Selected Row");

        String[] kolom = { "Nama Siswa", "Mata Pelajaran", "Nilai", "Index" };
        tableModel = new DefaultTableModel(kolom, 0);
        dataTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.SOUTH);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesHapus();
            }

        });

        return panel;
    }

    void prosesSimpan() {
        String nama = namaField.getText();
        String matpel = (String) matpelComboBox.getSelectedItem();
        String nilaiStr = nilaiField.getText();

        if (nama.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama siswa tidak boleh kosong!", "ErrorValidasi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (nama.length() <= 3) {
            JOptionPane.showMessageDialog(this, "Nama siswa harus lebih dari 3 karakter!", "ErrorValidasi",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nilai;
        try {
            nilai = Integer.parseInt(nilaiStr);
            if (nilai < 0 || nilai > 100) {
                JOptionPane.showMessageDialog(this, "Nilai harus antara 0 hingga 100!", "ErrorValidasi",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Nilai harus berupa angka!", "ErrorValidasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String index;
        index = switch (nilai / 10) {
            case 10, 9 -> "A";
            case 8 -> "B";
            case 7 -> "C";
            case 6 -> "D";
            default -> "E";

        };

        Object[] baris = { nama, matpel, nilai, index };
        tableModel.addRow(baris);

        JOptionPane.showMessageDialog(this, "Data siswa berhasil disimpan!", "Sukses",
                JOptionPane.INFORMATION_MESSAGE);
        namaField.setText("");
        nilaiField.setText("");
        matpelComboBox.setSelectedIndex(0);
        tabbedPane.setSelectedIndex(1);
    }

    void prosesHapus() {
        int row = dataTable.getSelectedRow();
        if (row > -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data ini?");
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(row);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada data yang dipilih", "ErrorNull",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    void prosesReset() {
        if (namaField.getText().isEmpty() && nilaiField.getText().isEmpty() && matpelComboBox.getSelectedIndex() == 0) {
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus data ini?");
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }
        namaField.setText("");
        nilaiField.setText("");
        matpelComboBox.setSelectedIndex(0);
    }
}
