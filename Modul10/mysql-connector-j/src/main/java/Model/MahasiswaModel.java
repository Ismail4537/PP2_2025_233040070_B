package Model;

import com.mysql.KoneksiDB;
import java.sql.*;

public class MahasiswaModel {
    public ResultSet loadData() throws SQLException {
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM mahasiswa");
        return result;
    }

    public void tambahData(String nama, String nim, String jurusan) throws SQLException {
        String sql = "INSERT INTO mahasiswa (nama, nim, jurusan) VALUES (?, ?, ?)";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, nim);
        pst.setString(3, jurusan);
        pst.execute();
    }

    public void updateData(String nama, String nim, String jurusan) throws SQLException {
        String sql = "UPDATE mahasiswa SET nama = ?, jurusan = ? WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nama);
        pst.setString(2, jurusan);
        pst.setString(3, nim);
        pst.executeUpdate();
    }

    public void deleteData(String nim) throws SQLException {
        String sql = "DELETE FROM mahasiswa WHERE nim = ?";
        Connection conn = KoneksiDB.configDB();
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, nim);
        pst.executeUpdate();
    }

    public ResultSet searchBy(String name, String column) throws SQLException {
        Connection conn = KoneksiDB.configDB();
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery("SELECT * FROM mahasiswa WHERE " + column + " LIKE '%" + name + "%'");
        return result;
    }
}