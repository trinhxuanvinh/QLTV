package QLDNDK;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ketnoi {
    private Connection conn;
    public ketnoi() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dangnhap?useSSL=false&serverTimezone=UTC";
        this.conn = DriverManager.getConnection(url, "root", "10052004");
         this.conn.setAutoCommit(true);
    }

    // Hàm trả về kết nối
    public Connection getConnection() {
        return this.conn;
    }

    // Hàm kiểm tra đăng nhập
    public boolean checkLogin(String username, String password, String role) {
        String sql = "SELECT * FROM tbdangnhap WHERE username = ? AND password = ? AND role = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có kết quả, đăng nhập thành công
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean registerUser(String username, String password, String role, String sdt) {
    String sql = "INSERT INTO tbdangnhap (username, password, role, sdt) VALUES (?, ?, ?, ?)";
    try {
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, role);
        stmt.setString(4, sdt);
        conn.setAutoCommit(false);
        int rowsInserted = stmt.executeUpdate();
        System.out.println("Rows inserted: " + rowsInserted);
        if (rowsInserted > 0) {
            conn.commit();
            JOptionPane.showMessageDialog(null, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            conn.rollback();
            JOptionPane.showMessageDialog(null, "Lỗi thêm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException e) {
    System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
    e.printStackTrace();
}

    return false;
}
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        ketnoi kn = new ketnoi();
        if (kn.getConnection() == null) {
    System.out.println("Không thể kết nối đến database!");
} else {
    System.out.println("Kết nối thành công!");
}

}
}
