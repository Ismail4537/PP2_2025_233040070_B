package Modul8;

import Modul8.Controller.PersegiPanjangController;
import Modul8.Model.PersegiPanjangModel;
import Modul8.View.PersegiPanjangView;

public class PersegiPanjangMain {
    public static void main(String[] args) {
        PersegiPanjangModel model = new PersegiPanjangModel();
        PersegiPanjangView view = new PersegiPanjangView();
        PersegiPanjangController controller = new PersegiPanjangController(model, view);
        view.setVisible(true);
    }
}
