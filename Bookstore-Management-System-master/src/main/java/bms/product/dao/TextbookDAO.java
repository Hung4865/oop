package bms.product.dao;

import bms.product.Textbook;
import bms.connectDB.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TextbookDAO {

    public boolean addTextbook(Textbook textbook) {
        // Cập nhật đủ 15 tham số dấu ?
        String sql = "INSERT INTO Textbook VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, textbook.getId());
            stmt.setString(2, textbook.getName());
            stmt.setDouble(3, textbook.getCostPrice());
            stmt.setDouble(4, textbook.getSalePrice());
            stmt.setInt(5, textbook.getQuantity());
            stmt.setString(6, textbook.getUnit());
            stmt.setString(7, textbook.getOrigin());
            stmt.setString(8, textbook.getAuthor());
            stmt.setString(9, textbook.getPublisher());
            stmt.setInt(10, textbook.getPublicationYear());
            stmt.setString(11, textbook.getGenre());
            stmt.setString(12, textbook.getLanguage());
            stmt.setString(13, textbook.getSubject());
            stmt.setInt(14, textbook.getGrade());
            stmt.setString(15, textbook.getEduLevel());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTextbook(String id) {
        String sql = "DELETE FROM Textbook WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateTextbook(Textbook textbook) {
        String sql = "UPDATE Textbook SET name=?, cost_price=?, sale_price=?, quantity=?, unit=?, origin=?, author=?, publisher=?, publicationYear=?, genre=?, language=?, subject=?, grade=?, eduLevel=? WHERE id=?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, textbook.getName());
            stmt.setDouble(2, textbook.getCostPrice());
            stmt.setDouble(3, textbook.getSalePrice());
            stmt.setInt(4, textbook.getQuantity());
            stmt.setString(5, textbook.getUnit());
            stmt.setString(6, textbook.getOrigin());
            stmt.setString(7, textbook.getAuthor());
            stmt.setString(8, textbook.getPublisher());
            stmt.setInt(9, textbook.getPublicationYear());
            stmt.setString(10, textbook.getGenre());
            stmt.setString(11, textbook.getLanguage());
            stmt.setString(12, textbook.getSubject());
            stmt.setInt(13, textbook.getGrade());
            stmt.setString(14, textbook.getEduLevel());
            stmt.setString(15, textbook.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Textbook getTextbookById(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Textbook WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Textbook(
                            rs.getString("author"),
                            rs.getString("publisher"),
                            rs.getInt("publication_year"),
                            rs.getString("genre"),
                            rs.getString("language"),
                            rs.getString("subject"),
                            rs.getInt("grade"),
                            rs.getString("edu_level"),
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