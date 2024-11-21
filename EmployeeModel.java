import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private int employee_id;
    private String last_name;
    private String first_name;
    private String position;
    private double salary;

    // Getters and setters
    public int getEmployeeId() { 
        return employee_id; 
    }
    public void setEmployeeId(int employee_id) { 
        this.employee_id = employee_id; 
    }

    public String getLastName() { 
        return last_name; 
    }
    public void setLastName(String last_name) { 
        this.last_name = last_name; 
    }

    public String getFirstName() { 
        return first_name; 
    }
    public void setFirstName(String first_name) { 
        this.first_name = first_name; 
    }

    public String getPosition() { 
        return position; 
    }
    public void setPosition(String position) { 
        this.position = position; 
    }

    public double getSalary() { 
        return salary; 
    }
    public void setSalary(double salary) { 
        this.salary = salary; 
    }

    // SQL Queries

    // 3. a. Reading employee records
    public EmployeeModel getEmployeeById(int employee_id) {
        EmployeeModel employee = null;
        String query = "SELECT e.employee_id, e.last_name, e.first_name, e.position, e.salary "
                     + "FROM Employees e WHERE e.employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employee_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = new EmployeeModel();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setLastName(rs.getString("last_name"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getDouble("salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // 3. b. Calculate hourly rate and total salary based on position and hours worked
    // Assuming a standard work year of 2080 hours (40 hours per week, 52 weeks per year)
    public double calculateHourlyRate() {
        double annualSalary = this.salary; // Annual salary fetched from the database
        final int hoursPerYear = 2080;  // Standard full-time work hours per year (40 * 52)

        // Calculate hourly rate by dividing annual salary by the total hours worked per year
        return annualSalary / hoursPerYear;
    }

    public double calculateSalaryBasedOnHoursWorked(int hoursWorked) {
        double hourlyRate = calculateHourlyRate();
        return hourlyRate * hoursWorked; // Total salary based on hours worked
    }

    // 3. c. Fetching all employees for reporting purposes
    public List<EmployeeModel> getAllEmployees() {
        List<EmployeeModel> employees = new ArrayList<>();
        String query = "SELECT e.employee_id, e.last_name, e.first_name, e.position, e.salary "
                     + "FROM Employees e";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setLastName(rs.getString("last_name"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setPosition(rs.getString("position"));
                employee.setSalary(rs.getDouble("salary"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // 3. d. Updating employee salary based on position or role change
    public boolean updateEmployeeSalary(int employee_id, double new_salary) {
        String query = "UPDATE Employees e SET e.salary = ? WHERE e.employee_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDouble(1, new_salary);
            stmt.setInt(2, employee_id);
            int rows_updated = stmt.executeUpdate();
            return rows_updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. e. Adding a new employee record
    public boolean addEmployee(String last_name, String first_name, String position, double salary) {
        String query = "INSERT INTO Employees (last_name, first_name, position, salary) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, last_name);
            stmt.setString(2, first_name);
            stmt.setString(3, position);
            stmt.setDouble(4, salary);
            int rows_inserted = stmt.executeUpdate();
            return rows_inserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}