package LAT;

import LAT.Controller.MahasiswaController;
import LAT.Model.MahasiswaModel;
import LAT.View.MahasiswaView;

public class MahasiswaMain {
    public static void main(String[] args) {
        MahasiswaModel model = new MahasiswaModel();
        MahasiswaView view = new MahasiswaView();
        MahasiswaController controller = new MahasiswaController(model, view);

        view.setVisible(true);
    }
}
