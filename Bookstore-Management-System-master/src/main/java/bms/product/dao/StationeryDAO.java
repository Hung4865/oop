package bms.product.dao;

import bms.product.Stationery;
import bms.connectDB.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StationeryDAO {

    public boolean addStationery(Stationery stationery) {
        String sql = "INSERT INTO Stationery VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, stationery.getId());
            stmt.setString(2, stationery.getName());
            stmt.setDouble(3, stationery.getCostPrice());
            stmt.setDouble(4, stationery.getSalePrice());
            stmt.setInt(5, stationery.getQuantity());
            stmt.setString(6, stationery.getUnit());
            stmt.setString(7, stationery.getOrigin());
            stmt.setString(8, stationery.getType());
            stmt.setString(9, stationery.getManufacturer());
            stmt.setString(10, stationery.getMaterial());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStationery(String id) {
        String sql = "DELETE FROM Stationery WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStationery(Stationery stationery) {
        String sql = "UPDATE Stationery SET name=?, cost_price=?, sale_price=?, quantity=?, unit=?, origin=?, type=?, manufacturer=?, material=? WHERE id=?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, stationery.getName());
            stmt.setDouble(2, stationery.getCostPrice());
            stmt.setDouble(3, stationery.getSalePrice());
            stmt.setInt(4, stationery.getQuantity());
            stmt.setString(5, stationery.getUnit());
            stmt.setString(6, stationery.getOrigin());
            stmt.setString(7, stationery.getType());
            stmt.setString(8, stationery.getManufacturer());
            stmt.setString(9, stationery.getMaterial());
            stmt.setString(10, stationery.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stationery getStationeryById(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Stationery WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Stationery(
                            rs.getString("type"),
                            rs.getString("manufacturer"), // Đã sửa tên trường nếu DB lưu là "manufacturer" thay vì "manufacture"
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