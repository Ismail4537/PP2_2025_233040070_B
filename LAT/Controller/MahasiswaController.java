package LAT.Controller;

import LAT.Model.MahasiswaModel;
import LAT.View.MahasiswaView;
import java.awt.event.*;
import java.io.*;
import javax.swing.JOptionPane;

public class MahasiswaController {
    MahasiswaModel model;
    MahasiswaView view;
    String filePath;

    public MahasiswaController(MahasiswaModel model, MahasiswaView view) {
        this.model = model;
        this.view = view;
        this.filePath = "LAT/Storage/mahasiswa_data.txt";

        this.view.addCreateListener(new CreateListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addLoadListener(new LoadFromFileListener());
        this.view.addSaveListener(new SaveToFileListener());
    }

    class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nama = view.getNama();
            String npm = view.getNpm();
            String jurusanStr = view.getJurusan();

            if (nama.trim().isEmpty() || npm.trim().isEmpty()) {
                view.tampilError("Nama atau NPM tidak boleh kosong.", "Error Validasi");
                return;
            }

            if (nama.length() < 3) {
                view.tampilError("Nama harus memiliki minimal 3 karakter.", "Error Validasi");
                return;
            }

            if (npm.length() != 8 || !npm.matches("\\d+")) {
                view.tampilError("NPM harus terdiri dari 8 digit angka.", "Error Validasi");
                return;
            }

            switch (jurusanStr) {
                case "Teknik Informatika" -> model.setJurusanStudi(MahasiswaModel.jurusan.TEKNIK_INFORMATIKA);
                case "Teknik Sipil" -> model.setJurusanStudi(MahasiswaModel.jurusan.TEKNIK_SIPIL);
                case "Teknik Elektro" -> model.setJurusanStudi(MahasiswaModel.jurusan.TEKNIK_ELEKTRO);
                case "Teknik Mesin" -> model.setJurusanStudi(MahasiswaModel.jurusan.TEKNIK_MESIN);
                default -> throw new IllegalArgumentException("Jurusan tidak valid");
            }
            model.setNama(nama);
            model.setNpm(npm);

            Object[] rowData = { model.getNama(), model.getNpm(), model.getJurusanStudi() };
            view.getTableModel().addRow(rowData);
            view.resetFields();
            saveToFile();

            JOptionPane.showMessageDialog(view, "Data mahasiswa berhasil disimpan!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int row = view.getTable().getSelectedRow();
            if (row > -1) {
                int confirm = JOptionPane.showConfirmDialog(view, "Anda yakin ingin menghapus data ini?");
                if (confirm == JOptionPane.YES_OPTION) {
                    view.getTableModel().removeRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Tidak ada data yang dipilih", "ErrorNull",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    class LoadFromFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadFromFile();
        }
    }

    class SaveToFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveToFile();
        }
    }

    void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < view.getTableModel().getRowCount(); i++) {
                String nama = view.getTableModel().getValueAt(i, 0).toString();
                String npm = view.getTableModel().getValueAt(i, 1).toString();
                String jurusan = view.getTableModel().getValueAt(i, 2).toString();
                writer.write(nama + "," + npm + "," + jurusan);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(view, "Data berhasil disimpan ke file.", "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(view, "Gagal menyimpan data ke file.", "ErrorIO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            view.getTableModel().setRowCount(0); // Clear existing data
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Object[] rowData = { parts[0], parts[1], parts[2] };
                    view.getTableModel().addRow(rowData);
                }
            }
            JOptionPane.showMessageDialog(view, "Data berhasil dimuat dari file.", "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(view, "Gagal memuat data dari file.", "ErrorIO",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}