package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MahasiswaView extends JFrame {
    JTextField txtNama, txtNIM, txtJurusan, txtCari;
    JButton btnSimpan, btnEdit, btnHapus, btnClear, btnCari;
    JComboBox<Object> filter;
    JTable tableMahasiswa;
    DefaultTableModel model;

    public MahasiswaView() {
        setTitle("CRUD JBDC");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createPanelAtas(), BorderLayout.NORTH);
        add(createScrollPaneTable(), BorderLayout.CENTER);
    }

    final JPanel createPanelAtas() {
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(createPanelForm(), BorderLayout.NORTH);
        panelAtas.add(createPanelButton(), BorderLayout.CENTER);
        panelAtas.add(createPanelCari(), BorderLayout.SOUTH);
        return panelAtas;
    }

    JPanel createPanelForm() {
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

        panelForm.add(new JLabel("Nama : "));
        txtNama = new JTextField();
        panelForm.add(txtNama);

        panelForm.add(new JLabel("NIM : "));
        txtNIM = new JTextField();
        panelForm.add(txtNIM);

        panelForm.add(new JLabel("Jurusan :"));
        txtJurusan = new JTextField();
        panelForm.add(txtJurusan);
        return panelForm;
    }

    JPanel createPanelButton() {
        JPanel panelTombol = new JPanel(new FlowLayout());
        btnSimpan = new JButton("Simpan");
        btnEdit = new JButton("Edit");
        btnHapus = new JButton("Hapus");
        btnClear = new JButton("Clear");

        panelTombol.add(btnSimpan);
        panelTombol.add(btnEdit);
        panelTombol.add(btnHapus);
        panelTombol.add(btnClear);
        return panelTombol;
    }

    JPanel createPanelCari() {
        JPanel panelCari = new JPanel(new FlowLayout());
        panelCari.add(new JLabel("Cari :"));
        txtCari = new JTextField(15);
        btnCari = new JButton("Cari");
        String[] filterOptions = { "Nama", "NIM", "Jurusan" };
        filter = new JComboBox<>(filterOptions);
        panelCari.add(txtCari);
        panelCari.add(filter);
        panelCari.add(btnCari);
        return panelCari;
    }

    final JScrollPane createScrollPaneTable() {
        model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Nama");
        model.addColumn("NIM");
        model.addColumn("Jurusan");

        tableMahasiswa = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tableMahasiswa);
        return scrollPane;
    }

    // Add listener methods
    public void addCreateListener(ActionListener listener) {
        btnSimpan.addActionListener(listener);
    }

    public void addUpdateListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addDeleteListener(ActionListener listener) {
        btnHapus.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        btnCari.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public void addTableMouseListener(MouseListener listener) {
        tableMahasiswa.addMouseListener(listener);
    }

    // Getter methods for input fields
    public String getNama() {
        return txtNama.getText();
    }

    public String getNIM() {
        return txtNIM.getText();
    }

    public String getJurusan() {
        return txtJurusan.getText();
    }

    public String getCari() {
        return txtCari.getText();
    }

    public String getFilter() {
        return (String) filter.getSelectedItem();
    }

    public JTable getTableMahasiswa() {
        return tableMahasiswa;
    }

    public DefaultTableModel getTableModel() {
        return model;
    }

    public void setFormFields(String nama, String nim, String jurusan) {
        txtNama.setText(nama);
        txtNIM.setText(nim);
        txtJurusan.setText(jurusan);
    }

    // Method to reset input fields
    public void resetFormFields() {
        txtNama.setText("");
        txtNIM.setText("");
        txtJurusan.setText("");
    }

    public void showError(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
