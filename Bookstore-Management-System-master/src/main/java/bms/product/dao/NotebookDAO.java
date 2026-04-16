package bms.product.dao;

import bms.product.Notebook;
import bms.connectDB.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotebookDAO {

    public boolean addNotebook(Notebook notebook) {
        String sql = "INSERT INTO Notebook VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, notebook.getId());
            stmt.setString(2, notebook.getName());
            stmt.setDouble(3, notebook.getCostPrice());
            stmt.setDouble(4, notebook.getSalePrice());
            stmt.setInt(5, notebook.getQuantity());
            stmt.setString(6, notebook.getUnit());
            stmt.setString(7, notebook.getOrigin());
            stmt.setInt(8, notebook.getPageCount());
            stmt.setString(9, notebook.getPaperType());
            stmt.setString(10, notebook.getSize());
            stmt.setString(11, notebook.getManufacturer());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNotebook(String id) {
        String sql = "DELETE FROM Notebook WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNotebook(Notebook notebook) {
        String sql = "UPDATE Notebook SET name=?, cost_price=?, sale_price=?, quantity=?, unit=?, origin=?, pageCount=?, paperType=?, size=?, manufacturer=? WHERE id=?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, notebook.getName());
            stmt.setDouble(2, notebook.getCostPrice());
            stmt.setDouble(3, notebook.getSalePrice());
            stmt.setInt(4, notebook.getQuantity());
            stmt.setString(5, notebook.getUnit());
            stmt.setString(6, notebook.getOrigin());
            stmt.setInt(7, notebook.getPageCount());
            stmt.setString(8, notebook.getPaperType());
            stmt.setString(9, notebook.getSize());
            stmt.setString(10, notebook.getManufacturer());
            stmt.setString(11, notebook.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Notebook getNotebookById(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Notebook WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Notebook(
                            rs.getInt("page_count"),
                            rs.getString("paper_type"),
                            rs.getString("size"),
                            rs.getString("manufacturer"),
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