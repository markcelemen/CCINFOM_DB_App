import java.sql.*;

public class EmployeeModel {
    private int employee_id;
    private String first_name;
    private String last_name;
    private String position;
    private double salary;

    // Getters and setters
    public int getEmployeeId() { return employee_id; }
    public void setEmployeeId(int employee_id) { this.employee_id = employee_id; }

    public String getFirstName() { return first_name; }
    public void setFirstName(String first_name) { this.first_name = first_name; }

    public String getLastName() { return last_name; }
    public void setLastName(String last_name) { this.last_name = last_name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    // SQL Queries

    // 3. a. Reading employee records
    public EmployeeModel getEmployeeById(int employee_id) {
        EmployeeModel employee = null;
        String query = "SELECT employee_id, first_name, last_name, position, salary FROM Employee WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employee_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new EmployeeModel();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // 3. b. Deleting an employee record
    public boolean deleteEmployee(int employee_id) {
        String query = "DELETE FROM Employee WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employee_id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. c. Generating payroll for employee(s)
    public boolean generatePayroll(int employee_id, double deduction) {
        String query = "INSERT INTO Payroll (employee_id, total_salary) "
                     + "SELECT employee_id, salary - ? FROM Employee WHERE employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, deduction);
            stmt.setInt(2, employee_id);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}