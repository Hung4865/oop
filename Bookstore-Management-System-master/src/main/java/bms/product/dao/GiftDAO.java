package bms.product.dao;

import bms.product.Gift;
import bms.connectDB.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GiftDAO {

    // Thêm một món quà vào cơ sở dữ liệu
    public boolean addGift(Gift gift) {
        String sql = "INSERT INTO Gift VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, gift.getId());
            stmt.setString(2, gift.getName());
            stmt.setDouble(3, gift.getCostPrice());
            stmt.setDouble(4, gift.getSalePrice());
            stmt.setInt(5, gift.getQuantity());
            stmt.setString(6, gift.getUnit());
            stmt.setString(7, gift.getOrigin());
            stmt.setString(8, gift.getType());
            stmt.setString(9, gift.getMaterial());

            int row = stmt.executeUpdate();
            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một món quà khỏi cơ sở dữ liệu
    public boolean deleteGift(String id) {
        String sql = "DELETE FROM Gift WHERE id = ?";
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

    // Cập nhật thông tin toàn bộ đối tượng (Cách an toàn)
    public boolean updateGift(Gift gift) {
        String sql = "UPDATE Gift SET name = ?, cost_price = ?, sale_price = ?, quantity = ?, unit = ?, origin = ?, type = ?, material = ? WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, gift.getName());
            stmt.setDouble(2, gift.getCostPrice());
            stmt.setDouble(3, gift.getSalePrice());
            stmt.setInt(4, gift.getQuantity());
            stmt.setString(5, gift.getUnit());
            stmt.setString(6, gift.getOrigin());
            stmt.setString(7, gift.getType());
            stmt.setString(8, gift.getMaterial());
            stmt.setString(9, gift.getId());

            int row = stmt.executeUpdate();
            return row > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin quà tặng theo ID
    public Gift getGiftById(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Gift WHERE id = ?";

        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Gift(
                            rs.getString("type"),
                            rs.getString("material"),
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