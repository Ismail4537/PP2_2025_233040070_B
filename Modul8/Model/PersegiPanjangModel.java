package Modul8.Model;

public class PersegiPanjangModel {
    double panjang, lebar, luas, keliling;

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public void hitungLuas() {
        this.luas = this.panjang * this.lebar;
    }

    public void hitungKeliling() {
        this.keliling = 2 * (this.panjang + this.lebar);
    }

    public double getPanjang() {
        return this.panjang;
    }

    public double getLebar() {
        return this.lebar;
    }

    public double getLuas() {
        return this.luas;
    }

    public double getKeliling() {
        return this.keliling;
    }
}
