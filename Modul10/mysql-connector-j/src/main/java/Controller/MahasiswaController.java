package Controller;

import Model.MahasiswaModel;
import View.MahasiswaView;
import java.awt.event.*;
import java.sql.*;

public class MahasiswaController {
    MahasiswaModel model;
    MahasiswaView view;

    public MahasiswaController(MahasiswaModel model, MahasiswaView view) {
        this.model = model;
        this.view = view;

        this.view.addTableMouseListener(new TableMouseListener());
        this.view.addCreateListener(new CreateListener());
        this.view.addUpdateListener(new UpdateListener());
        this.view.addDeleteListener(new DeleteListener());
        this.view.addSearchListener(new SearchListener());
        this.view.addClearListener(new ClearListener());
        loadData();
    }

    class CreateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getNama().isEmpty() || view.getNIM().isEmpty() || view.getJurusan().isEmpty()) {
                view.showError("Semua field harus diisi.", "Input Error");
                return;
            }
            try {
                if (model.searchBy(view.getNIM(), "nim").next()) {
                    view.showError("NIM sudah ada.", "Input Error");
                    return;
                }
                model.tambahData(view.getNama(), view.getNIM(), view.getJurusan());
                view.resetFormFields();
                loadData();
                view.showMessage("Data berhasil ditambahkan.", "Success");
            } catch (SQLException ex) {
                view.showError("Error adding data: " + ex.getMessage(), "SQL Error");
            }
        }
    }

    class UpdateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getNama().isEmpty() || view.getNIM().isEmpty() || view.getJurusan().isEmpty()) {
                view.showError("Semua field harus diisi.", "Input Error");
                return;
            }
            try {
                model.updateData(view.getNama(), view.getNIM(), view.getJurusan());
                view.resetFormFields();
                loadData();
                view.showMessage("Data berhasil diupdate.", "Success");
            } catch (SQLException ex) {
                view.showError("Error updating data: " + ex.getMessage(), "SQL Error");
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getNIM().isEmpty()) {
                view.showError("Pilih data yang akan dihapus.", "Input Error");
                return;
            }
            try {
                model.deleteData(view.getNIM());
                view.resetFormFields();
                loadData();
                view.showMessage("Data berhasil dihapus.", "Success");
            } catch (SQLException ex) {
                view.showError("Error deleting data: " + ex.getMessage(), "SQL Error");
            }
        }
    }

    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (view.getCari().isEmpty()) {
                loadData();
                return;
            }
            try {
                loadToTable(model.searchBy(view.getCari(), view.getFilter()));
            } catch (SQLException ex) {
                view.showError("Error searching data: " + ex.getMessage(), "SQL Error");
            }
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetFormFields();
        }
    }

    class TableMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = view.getTableMahasiswa().getSelectedRow();
            view.setFormFields(
                    view.getTableModel().getValueAt(row, 1).toString(),
                    view.getTableModel().getValueAt(row, 2).toString(),
                    view.getTableModel().getValueAt(row, 3).toString());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private void loadToTable(ResultSet result) {
        try {
            view.getTableModel().setRowCount(0); // Reset tabel
            int no = 1;
            while (result.next()) {
                view.getTableModel().addRow(new Object[] {
                        no++,
                        result.getString("nama"),
                        result.getString("nim"),
                        result.getString("jurusan")
                });
            }
        } catch (SQLException ex) {
            view.showError("Error loading data: " + ex.getMessage(), "SQL Error");
        }
    }

    private void loadData() {
        try {
            ResultSet result = model.loadData();
            loadToTable(result);
        } catch (SQLException ex) {
            view.showError("Error loading data: " + ex.getMessage(), "SQL Error");
        }
    }
}
