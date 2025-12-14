package LAT.View;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MahasiswaView extends JFrame {
    JTextField fieldNama = new JTextField(50);
    JTextField fieldNpm = new JTextField(8);
    JTabbedPane tabbedPane = new JTabbedPane();
    JButton btnSubmit = new JButton("Submit");
    JButton btnDelete = new JButton("Delete");
    JButton btnLoad = new JButton("Load Data");
    JButton btnSave = new JButton("Save Data");
    JTable dataTable;
    DefaultTableModel tableModel;
    JComboBox<String> comboJurusan;

    public MahasiswaView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setTitle("Daftar Mahasiswa");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        tabbedPane.addTab("Input", inputPanel);

        JPanel tablePanel = createTablePanel();
        tabbedPane.addTab("Table", tablePanel);
        add(tabbedPane, BorderLayout.CENTER);
    }

    JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 5, 5));
        panel.add(new JLabel("Nama:"));
        panel.add(fieldNama);
        panel.add(new JLabel("NPM:"));
        panel.add(fieldNpm);
        panel.add(new JLabel("Jurusan:"));
        String[] jurusanOptions = { "Teknik Informatika", "Teknik Sipil", "Teknik Elektro", "Teknik Mesin" };
        comboJurusan = new JComboBox<>(jurusanOptions);
        panel.add(comboJurusan);
        panel.add(btnSubmit);
        return panel;
    }

    JPanel createTablePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel btnPanel = new JPanel();
        String[] columnNames = { "Nama", "NPM", "Jurusan" };
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);

        TableColumn jurusanColumn = dataTable.getColumnModel().getColumn(2);
        String[] jurusanOptions = { "TEKNIK_INFORMATIKA", "TEKNIK_SIPIL", "TEKNIK_ELEKTRO", "TEKNIK_MESIN" };
        JComboBox<String> comboBox = new JComboBox<>(jurusanOptions);
        jurusanColumn.setCellEditor(new DefaultCellEditor(comboBox));

        btnPanel.add(btnSave);
        btnPanel.add(btnLoad);
        btnPanel.add(btnDelete);

        JScrollPane scrollPane = new JScrollPane(dataTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        return panel;
    }

    public void tampilError(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public String getNama() {
        return fieldNama.getText();
    }

    public String getNpm() {
        return fieldNpm.getText();
    }

    public String getJurusan() {
        return (String) comboJurusan.getSelectedItem();
    }

    public void addCreateListener(ActionListener listener) {
        btnSubmit.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addLoadListener(ActionListener listener) {
        btnLoad.addActionListener(listener);
    }

    public void addSaveListener(ActionListener listener) {
        btnSave.addActionListener(listener);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getTable() {
        return dataTable;
    }

    public void resetFields() {
        fieldNama.setText("");
        fieldNpm.setText("");
        comboJurusan.setSelectedIndex(0);
    }
}
