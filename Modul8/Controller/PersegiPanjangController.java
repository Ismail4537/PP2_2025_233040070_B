package Modul8.Controller;

import Modul8.Model.PersegiPanjangModel;
import Modul8.View.PersegiPanjangView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersegiPanjangController {
    PersegiPanjangModel model;
    PersegiPanjangView view;

    public PersegiPanjangController(PersegiPanjangModel model, PersegiPanjangView view) {
        this.model = model;
        this.view = view;

        this.view.addLuasListener(new LuasListener());
        this.view.addKelilingListener(new KelilingListener());
        this.view.addResetListener(new ResetListener());
    }

    class LuasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double panjang = view.getPanjang();
                double lebar = view.getLebar();

                model.setPanjang(panjang);
                model.setLebar(lebar);

                model.hitungLuas();

                double hasilLuas = model.getLuas();
                view.setHasil(hasilLuas);

            } catch (NumberFormatException ex) {
                view.tampilError("Input tidak valid. Harap masukkan angka.");
            }
        }
    }

    class KelilingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double panjang = view.getPanjang();
                double lebar = view.getLebar();

                model.setPanjang(panjang);
                model.setLebar(lebar);

                model.hitungKeliling();

                double hasilKeliling = model.getKeliling();
                view.setHasil(hasilKeliling);

            } catch (NumberFormatException ex) {
                view.tampilError("Input tidak valid. Harap masukkan angka.");
            }
        }
    }

    class ResetListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.resetInputan();
        }
    }
}