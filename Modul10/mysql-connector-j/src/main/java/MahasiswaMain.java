import Controller.MahasiswaController;
import Model.MahasiswaModel;
import View.MahasiswaView;

public class MahasiswaMain {

    public static void main(String[] args) {
        // Menjalankan Aplikasi
        MahasiswaModel model = new MahasiswaModel();
        MahasiswaView view = new MahasiswaView();
        MahasiswaController controller = new MahasiswaController(model, view);

        view.setVisible(true);
    }
}