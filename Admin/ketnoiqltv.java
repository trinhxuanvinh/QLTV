/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;
import QLDNDK.ketnoi;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 10052
 */
public class ketnoiqltv {
    private Connection conn;
    public ketnoiqltv() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/qltv?useSSL=false&serverTimezone=UTC";
        this.conn = DriverManager.getConnection(url, "root", "10052004");
         this.conn.setAutoCommit(true);
         
    }
        public Connection getConnection() {
        return this.conn;
    }
    public ResultSet getData(String tbname) throws SQLException {
        Statement ts = this.conn.createStatement();
        String sql = "SELECT * FROM sach " + tbname;
        System.out.println("SQL Query: " + sql);
        ResultSet rs = ts.executeQuery(sql);
        return rs;
    }
    public void insertSach(int maSach, String tenSach, String tacGia, String theLoai,String nhaXuatBan, int namXuatBan, String ISBN,int soLuong, String viTri, String trangThai, String maNhaCungCap) throws SQLException {
    String sql = "INSERT INTO sach (MaSach, Tensach, TacGia, TheLoai, NhaXuatBan, NamXuatBan, ISBN, SoLuong, ViTri, TrangThai, MaNhaCungCap) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, maSach);
        pstmt.setString(2, tenSach);
        pstmt.setString(3, tacGia);
        pstmt.setString(4, theLoai);
        pstmt.setString(5, nhaXuatBan);
        pstmt.setInt(6, namXuatBan);
        pstmt.setString(7, ISBN);
        pstmt.setInt(8, soLuong);
        pstmt.setString(9, viTri);
        pstmt.setString(10, trangThai);
        pstmt.setString(11, maNhaCungCap);

        pstmt.executeUpdate();
    }
}
    public void deleteSach(int maSach) throws SQLException {
    String sql = "DELETE FROM sach WHERE MaSach = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, maSach);
        pstmt.executeUpdate();
    }
}
    public void updateSach(int maSach, String tenSach, String tacGia, String theLoai,
                           String nhaXuatBan, int namXuatBan, String ISBN,
                           int soLuong, String viTri, String trangThai,
                           String maNhaCungCap) throws SQLException {

        String sql = "UPDATE sach SET TenSach=?, TacGia=?, TheLoai=?, NhaXuatBan=?, NamXuatBan=?, " +
                     "ISBN=?, SoLuong=?, ViTri=?, TrangThai=?, MaNhaCungCap=? WHERE MaSach=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tenSach);
            pstmt.setString(2, tacGia);
            pstmt.setString(3, theLoai);
            pstmt.setString(4, nhaXuatBan);
            pstmt.setInt(5, namXuatBan);
            pstmt.setString(6, ISBN);
            pstmt.setInt(7, soLuong);
            pstmt.setString(8, viTri);
            pstmt.setString(9, trangThai);
            pstmt.setString(10, maNhaCungCap);
            pstmt.setInt(11, maSach);

            pstmt.executeUpdate();
        }
    }
    public ResultSet timKiemSach(String truong, String tuKhoa) throws SQLException, ClassNotFoundException {
    Connection conn = getConnection(); // Hoặc conn nếu có sẵn
    String sql = "SELECT * FROM sach WHERE " + truong + " LIKE ?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, "%" + tuKhoa + "%");
    return pstmt.executeQuery();
}
    public ResultSet getDatanv(String tbname) throws SQLException {
        Statement ts = this.conn.createStatement();
        String sql = "SELECT * FROM nhanvien " + tbname;
        System.out.println("SQL Query: " + sql);
        ResultSet rs = ts.executeQuery(sql);
        return rs;
    }
    public void insertNhanVien(int maNV, String hoTen, String chucVu, String sdt, String email) throws SQLException {
        String sql = "INSERT INTO nhanvien (MaNhanVien, HoTen, ChucVu, SoDienThoai, Email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maNV);
            pstmt.setString(2, hoTen);
            pstmt.setString(3, chucVu);
            pstmt.setString(4, sdt);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
        }
    }
    public void deleteNhanVien(int maNV) throws SQLException {
    String sql = "DELETE FROM nhanvien WHERE MaNhanVien = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, maNV);
        pstmt.executeUpdate();
    }
}
    public void updateNhanVien(int maNV, String hoTen, String chucVu, String sdt, String email) throws SQLException {
    String sql = "UPDATE nhanvien SET HoTen=?, ChucVu=?, SoDienThoai=?, Email=? WHERE MaNhanVien=?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, hoTen);
        pstmt.setString(2, chucVu);
        pstmt.setString(3, sdt);
        pstmt.setString(4, email);
        pstmt.setInt(5, maNV);

        pstmt.executeUpdate();
    }
}



    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        ketnoiqltv kntl = new ketnoiqltv();
        if (kntl.getConnection() == null) {
    System.out.println("Không thể kết nối đến database!");
        } 
        else {
            System.out.println("Kết nối thành công!");
        }
    }

}
                    