package LAT.Model;

public class MahasiswaModel {
    String nama, npm;
    jurusan jurusanStudi;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public jurusan getJurusanStudi() {
        return jurusanStudi;
    }

    public void setJurusanStudi(jurusan jurusanStudi) {
        this.jurusanStudi = jurusanStudi;
    }

    public enum jurusan {
        TEKNIK_INFORMATIKA,
        TEKNIK_SIPIL,
        TEKNIK_ELEKTRO,
        TEKNIK_MESIN
    }
}
