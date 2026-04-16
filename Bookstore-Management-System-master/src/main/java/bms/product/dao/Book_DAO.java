package bms.product.dao;
import bms.product.Book;
import bms.connectDB.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class Book_DAO {
    // Truyền hẳn 1 object Book vào thay vì truyền 12 tham số riêng lẻ
    public boolean addBook(Book book) {
        String sql = "INSERT INTO Book VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, book.getId());
            stmt.setString(2, book.getName());
            stmt.setDouble(3, book.getCostPrice());
            stmt.setDouble(4, book.getSalePrice());
            stmt.setInt(5, book.getQuantity());
            stmt.setString(6, book.getUnit());
            stmt.setString(7, book.getOrigin());
            stmt.setString(8, book.getAuthor());
            stmt.setString(9, book.getPublisher());
            stmt.setInt(10, book.getPublicationYear());
            stmt.setString(11, book.getGenre());
            stmt.setString(12, book.getLanguage());

            int row = stmt.executeUpdate();
            return row > 0; // Trả về true nếu thêm thành công

        } catch (Exception e) {
            e.printStackTrace(); // In ra lỗi để debug
            return false;
        }
    }

    public boolean deleteBook(String id) {
        String sql = "DELETE FROM Book WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            int row = stmt.executeUpdate();
            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBook(String col, String val, String id) {
        // CẢNH BÁO NHỎ: Việc cộng chuỗi tên cột (" + col + ") có thể gây lỗi SQL Injection.
        // Trong dự án thực tế, bạn nên update theo object Book, hoặc kiểm tra kỹ biến 'col'.
        String sql = "UPDATE Book SET " + col + " = ? WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, val);
            stmt.setString(2, id);

            int row = stmt.executeUpdate();
            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa từ khóa 'static' vì trong mô hình DAO, ta thường tạo instance của BookDAO để gọi hàm
    public Book getBookById(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM book WHERE id = ?";

        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Book(
                            rs.getString("author"),
                            rs.getString("publisher"),
                            rs.getInt("publicationYear"),
                            rs.getString("genre"),
                            rs.getString("language"),
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getDouble("cost_price"),
                            rs.getDouble("sale_price"),
                            rs.getInt("quantity"),
                            rs.getString("unit"),
                            rs.getString("origin")
                    );
                }
            }
        }
        return null;
    }
}
