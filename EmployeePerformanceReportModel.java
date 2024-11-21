import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePerformanceReportModel {
    private String employee_name;
    private int orders_processed;
    private double total_sales;

    // Getters and setters
    public String getEmployeeName() { 
        return employee_name; 
    }
    public void setEmployeeName(String employee_name) { 
        this.employee_name = employee_name; 
    }

    public int getOrdersProcessed() { 
        return orders_processed; 
    }
    public void setOrdersProcessed(int orders_processed) { 
        this.orders_processed = orders_processed;
    }

    public double getTotalSales() { 
        return total_sales; 
    }
    public void setTotalSales(double total_sales) { 
        this.total_sales = total_sales; 
    }

    // SQL Query: Employee Performance Report by Day, Month, or Year
    public List<EmployeePerformanceReportModel> getEmployeePerformanceReport(String period) {
        List<EmployeePerformanceReportModel> reports = new ArrayList<>();
        String query = "";

        switch (period.toLowerCase()) {
            case "day":
                query = "SELECT e.first_name, e.last_name, COUNT(o.order_id) AS orders_processed, SUM(o.total_amount) AS total_sales " +
                        "FROM Orders o " +
                        "JOIN Employees e ON o.employee_id = e.employee_id " +
                        "WHERE DATE(o.order_date) = CURDATE() " +
                        "GROUP BY e.employee_id";
                break;
            case "month":
                query = "SELECT e.first_name, e.last_name, COUNT(o.order_id) AS orders_processed, SUM(o.total_amount) AS total_sales " +
                        "FROM Orders o " +
                        "JOIN Employees e ON o.employee_id = e.employee_id " +
                        "WHERE MONTH(o.order_date) = MONTH(CURDATE()) " +
                        "GROUP BY e.employee_id";
                break;
            case "year":
                query = "SELECT e.first_name, e.last_name, COUNT(o.order_id) AS orders_processed, SUM(o.total_amount) AS total_sales " +
                        "FROM Orders o " +
                        "JOIN Employees e ON o.employee_id = e.employee_id " +
                        "WHERE YEAR(o.order_date) = YEAR(CURDATE()) " +
                        "GROUP BY e.employee_id";
                break;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                EmployeePerformanceReportModel report = new EmployeePerformanceReportModel();
                report.setEmployeeName(rs.getString("first_name") + " " + rs.getString("last_name"));
                report.setOrdersProcessed(rs.getInt("orders_processed"));
                report.setTotalSales(rs.getDouble("total_sales"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}