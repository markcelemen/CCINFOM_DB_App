import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePerformanceReportModel {
    private int employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private Date reportDate;
    private int totalOrdersHandled;
    private double totalSalesHandled;

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public Date getReportDate() {
        return reportDate;
    }
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getTotalOrdersHandled() {
        return totalOrdersHandled;
    }
    public void setTotalOrdersHandled(int totalOrdersHandled) {
        this.totalOrdersHandled = totalOrdersHandled;
    }

    public double getTotalSalesHandled() {
        return totalSalesHandled;
    }
    public void setTotalSalesHandled(double totalSalesHandled) {
        this.totalSalesHandled = totalSalesHandled;
    }

    // SQL Query: Employee Performance Report by Day, Month, or Year
    public List<EmployeePerformanceReportModel> getEmployeePerformance(String period, String startDate, String endDate) {
        List<EmployeePerformanceReportModel> employees = new ArrayList<>();
        String query = "";

        switch (period.toLowerCase()) {
            case "day":
                query = "SELECT e.employee_id, e.first_name, e.last_name, e.position, o.order_date AS report_date, " +
                        "COUNT(o.order_id) AS total_orders_handled, SUM(o.total_amount) AS total_sales_handled " +
                        "FROM Employee e " +
                        "JOIN Orders o ON e.employee_id = o.employee_id " +
                        "WHERE o.order_date = ? " +
                        "GROUP BY e.employee_id, o.order_date " +
                        "ORDER BY total_sales_handled DESC";
                break;
            case "month":
                query = "SELECT e.employee_id, e.first_name, e.last_name, e.position, MONTH(o.order_date) AS month, " +
                        "YEAR(o.order_date) AS year, COUNT(o.order_id) AS total_orders_handled, SUM(o.total_amount) AS total_sales_handled " +
                        "FROM Employee e " +
                        "JOIN Orders o ON e.employee_id = o.employee_id " +
                        "WHERE o.order_date BETWEEN ? AND ? " +
                        "GROUP BY e.employee_id, MONTH(o.order_date), YEAR(o.order_date) " +
                        "ORDER BY total_sales_handled DESC";
                break;
            case "year":
                query = "SELECT e.employee_id, e.first_name, e.last_name, e.position, YEAR(o.order_date) AS year, " +
                        "COUNT(o.order_id) AS total_orders_handled, SUM(o.total_amount) AS total_sales_handled " +
                        "FROM Employee e " +
                        "JOIN Orders o ON e.employee_id = o.employee_id " +
                        "WHERE o.order_date BETWEEN ? AND ? " +
                        "GROUP BY e.employee_id, YEAR(o.order_date) " +
                        "ORDER BY total_sales_handled DESC";
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, startDate);
            stmt.setString(2, endDate);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeePerformanceReportModel employee = new EmployeePerformanceReportModel();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPosition(rs.getString("position"));
                employee.setReportDate(rs.getDate("report_date"));
                employee.setTotalOrdersHandled(rs.getInt("total_orders_handled"));
                employee.setTotalSalesHandled(rs.getDouble("total_sales_handled"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}