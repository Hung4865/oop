package bms.user.dao;
import bms.user.Employee;
import bms.connectDB.ConnectMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class EmployeeDAO {
    // Thêm một nhân viên vào cơ sở dữ liệu
    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getBirth());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhoneNumber());
            stmt.setString(6, employee.getEmail());
            stmt.setString(7, employee.getPosition());
            stmt.setString(8, employee.getEmploymentType());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa một nhân viên khỏi cơ sở dữ liệu
    public boolean deleteEmployee(String id) {
        String sql = "DELETE FROM Employee WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin nhân viên trong cơ sở dữ liệu
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE Employee SET name = ?, birth = ?, address = ?, phoneNumber = ?, email = ?, position = ?, employmentType = ? WHERE id = ?";
        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getBirth());
            stmt.setString(3, employee.getAddress());
            stmt.setString(4, employee.getPhoneNumber());
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getPosition());
            stmt.setString(7, employee.getEmploymentType());
            stmt.setString(8, employee.getId());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Truy xuất thông tin nhân viên theo mã ID
    public Employee getEmployeeById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Employee WHERE id = ?";

        try (Connection con = ConnectMySQL.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getString("position"),
                            rs.getString("employmentType"),
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("birth"),
                            rs.getString("address"),
                            rs.getString("phoneNumber"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }
}
